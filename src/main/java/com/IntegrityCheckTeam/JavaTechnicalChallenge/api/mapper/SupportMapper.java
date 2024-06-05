package com.IntegrityCheckTeam.JavaTechnicalChallenge.api.mapper;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.api.dto.ItemDTO;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.document.ItemDocument;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.enums.Currency;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.repository.ItemRepository;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        disableSubMappingMethodsGeneration = true,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ItemRepository.class})
public interface SupportMapper {
    ItemDTO mapToItemDTO(ItemDocument itemDocument);
    ItemDocument mapToItemDocument(ItemDTO itemDTO);
    List<ItemDTO> mapToListItemDTO(Collection<ItemDocument> items);
    List<ItemDocument> mapToListItemDocument(Collection<ItemDTO> items);
    default String mapCurrencyToName(Currency currency) {
        return currency.getName();
    }

    default Currency mapNameToCurrency(String name) {
        return Currency.getCurrencyByName(name);
    }


}
