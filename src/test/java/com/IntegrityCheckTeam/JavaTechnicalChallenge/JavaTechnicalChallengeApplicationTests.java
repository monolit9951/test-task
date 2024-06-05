package com.IntegrityCheckTeam.JavaTechnicalChallenge;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.api.dto.ItemDTO;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.ItemService;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.enums.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JavaTechnicalChallengeApplicationTests {
	@Autowired
	private ItemService itemService;

	@Test
	void saveAndGetItemByIdTest() {
		ItemDTO newItem = new ItemDTO();
		newItem.setName("Test Item");
		newItem.setAmountItem(1);
		newItem.setCurrency(Currency.USD);
		newItem.setPrice(BigDecimal.valueOf(10.99));
		newItem.setItemDescription("Test description");

		ItemDTO savedItem = itemService.save(newItem);

		ItemDTO retrievedItem = itemService.getItemById(savedItem.getId());

		assertEquals(newItem.getName(), retrievedItem.getName());
		assertEquals(newItem.getAmountItem(), retrievedItem.getAmountItem());
		assertEquals(newItem.getCurrency(), retrievedItem.getCurrency());
		assertEquals(newItem.getPrice(), retrievedItem.getPrice());
		assertEquals(newItem.getItemDescription(), retrievedItem.getItemDescription());
	}

	@Test
	void updateTest() {
		ItemDTO newItem = new ItemDTO();
		newItem.setName("Test Item");
		newItem.setAmountItem(1);
		newItem.setCurrency(Currency.getCurrencyByName("USD"));
		newItem.setPrice(BigDecimal.valueOf(10.99));
		newItem.setItemDescription("Test description");

		ItemDTO savedItem = itemService.save(newItem);

		newItem.setName("Updated Test Item");
		itemService.update(savedItem.getId(), newItem);

		ItemDTO updatedItem = itemService.getItemById(savedItem.getId());

		assertEquals("Updated Test Item", updatedItem.getName());
	}

	@Test
	void deleteTest() {
		ItemDTO newItem = new ItemDTO();
		newItem.setName("Test Item");
		newItem.setAmountItem(1);
		newItem.setCurrency(Currency.getCurrencyByName("USD"));
		newItem.setPrice(BigDecimal.valueOf(10.99));
		newItem.setItemDescription("Test description");

		ItemDTO savedItem = itemService.save(newItem);

		itemService.delete(savedItem.getId());

		assertThrows(NoSuchElementException.class, () -> itemService.getItemById(savedItem.getId()));
	}


}
