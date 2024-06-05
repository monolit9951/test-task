package com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.api.dto.ItemDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {
    ItemDTO save(ItemDTO itemDTO);

    List<ItemDTO> saveAll(List<ItemDTO> itemDTO);

    ItemDTO getItemById(String id);

    ItemDTO update(String id, ItemDTO itemDTO);

    void delete(String id);

    Page<ItemDTO> getPageItemDTO(int page, int pageSize, String sortDirection);
}
