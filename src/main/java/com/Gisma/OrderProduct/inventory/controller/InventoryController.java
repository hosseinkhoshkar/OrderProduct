package com.Gisma.OrderProduct.inventory.controller;

import com.Gisma.OrderProduct.inventory.dto.InventoryDTO;
import com.Gisma.OrderProduct.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody InventoryDTO inventoryDTO){
        inventoryService.create(inventoryDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryDTO findById(@PathVariable Long id) {
        return inventoryService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryDTO update(@PathVariable Long id, @RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.update(id,inventoryDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        inventoryService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDTO> getAllProduct(){
        return inventoryService.findAll();
    }


}