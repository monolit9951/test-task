package com.IntegrityCheckTeam.JavaTechnicalChallenge.api.controller;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.api.dto.ItemDTO;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.HttpTraceService;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;
    private final HttpTraceService httpTraceService;

    public ItemController(ItemService itemService, HttpTraceService httpTraceService) {
        this.itemService = itemService;
        this.httpTraceService = httpTraceService;
    }

    @PostMapping
    @Operation(summary = "A query that saves the item to the database!",
            description = "A query that saves the item to the database!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The item has been successfully saved!")
    })
    public ItemDTO saveItem(@RequestBody @Valid ItemDTO itemDTO) {
        return itemService.save(itemDTO);
    }

    @PostMapping("/saveAll")
    @Operation(summary = "A query that saves the all items to the database!",
            description = "A query that saves the all items to the database!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The item has been successfully saved!")
    })
    public List<ItemDTO> saveAllItem(@RequestBody @Valid List<ItemDTO> itemDTO) {
        return itemService.saveAll(itemDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "A query that updates the item to the database!",
            description = "A query that updates the item to the database!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The item has been successfully updated!")
    })
    public ItemDTO updateItem(@PathVariable String id,
                              @RequestBody @Valid ItemDTO itemDTO) {
        return itemService.update(id, itemDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "The query that the item gives on his id!",
            description = "The query that the item gives on his id!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The item has been successfully received by id!")
    })
    public ItemDTO getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "A query that deletes a item by his id!",
            description = "A query that deletes a item by his id!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The item has been deleted successfully received by id!")
    })
    public void deletedItemById(@PathVariable String id) {
        itemService.delete(id);
    }

    @GetMapping
    @Operation(summary = "A query that returns a page of items!",
            description = "A request that returns a page of items!\n" +
                    "To get the page, you need to pass:\n" +
                    "int page - which is the page number,\n" +
                    "int pageSize - the number of records on the page,\n" +
                    "String sortDirection can be ASC or DESC, sorting the items by name.\n" +
                    "Example URL - http://localhost:8080/api/items?page=0&pageSize=10&sortDirection=ASC this request will return the first page with no more than 10 items sorted alphabetically by item name!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The items page has been successfully!")
    })
    public Page<ItemDTO> getPageItemDTO(@RequestParam int page,
                                        @RequestParam int pageSize,
                                        @RequestParam String sortDirection) {
        return itemService.getPageItemDTO(page, pageSize, sortDirection);
    }
}
