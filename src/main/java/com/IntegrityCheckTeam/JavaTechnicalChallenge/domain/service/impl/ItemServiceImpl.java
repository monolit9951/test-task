package com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.impl;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.api.dto.ItemDTO;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.api.mapper.SupportMapper;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.document.ItemDocument;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.ItemService;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.enums.SortDirection;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.repository.ItemRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.redis.core.RedisTemplate;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final SupportMapper mapper;
    private final RedisTemplate<String, ItemDocument> redisTemplate;
    private final MongoTemplate mongoTemplate;

    public ItemServiceImpl(ItemRepository itemRepository, SupportMapper supportMapper,
                           RedisTemplate<String, ItemDocument> redisTemplate, MongoTemplate mongoTemplate) {
        this.itemRepository = itemRepository;
        this.mapper = supportMapper;
        this.redisTemplate = redisTemplate;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        ItemDocument itemDocument = mapper.mapToItemDocument(itemDTO);
        ItemDocument savedItem = itemRepository.save(itemDocument);

        redisTemplate.opsForValue().set(savedItem.getId(), savedItem);

        return mapper.mapToItemDTO(savedItem);
    }

    @Override
    public List<ItemDTO> saveAll(List<ItemDTO> itemDTOs) {
        List<ItemDocument> itemDocuments = mapper.mapToListItemDocument(itemDTOs);
        List<ItemDocument> savedAll = itemRepository.saveAll(itemDocuments);
        return mapper.mapToListItemDTO(savedAll);
    }

    @Override
    public ItemDTO getItemById(String id) {
        ItemDocument itemDocument =redisTemplate.opsForValue().get(id);
        System.out.println("redis find");
        if(itemDocument == null) {
            System.out.println("db find");
            itemDocument = itemRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Can't find item by id = " + id));
        }
        return mapper.mapToItemDTO(itemDocument);
    }

    @Override
    public ItemDTO update(String id, ItemDTO itemDTO) {
        ItemDocument updateItem = mapper.mapToItemDocument(itemDTO);
        updateItem.setId(id);

        Update update = new Update();
        update.set("name", updateItem.getName());
        update.set("amountItem", updateItem.getAmountItem());
        update.set("currency", updateItem.getCurrency());
        update.set("price", updateItem.getPrice());
        update.set("itemDescription", updateItem.getItemDescription());

        UpdateResult result = mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(id)),
                update,
                ItemDocument.class
        );

        long numberOfFieldsUpdated = result.getModifiedCount();

        if (numberOfFieldsUpdated == 0) {
            throw new ResourceNotFoundException("No fields were updated for item with id: " + id);
        }

        return mapper.mapToItemDTO(updateItem);
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query, ItemDocument.class);

        if (deleteResult.getDeletedCount() == 0) {
            throw new ResourceNotFoundException("No item exists with the provided id: " + id);
        }
    }

    @Override
    public Page<ItemDTO> getPageItemDTO(int page, int pageSize, String sortDirection) {
        SortDirection direction = SortDirection.getSortDirectionByName(sortDirection);
        Sort.Direction springDirection = direction == SortDirection.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(springDirection, "name"));
        Page<ItemDocument> items = itemRepository.findPageItemDocumentBy(pageRequest);
        List<ItemDTO> itemDTOList = mapper.mapToListItemDTO(items.getContent());
        return new PageImpl<>(itemDTOList, pageRequest, items.getTotalElements());
    }
}
