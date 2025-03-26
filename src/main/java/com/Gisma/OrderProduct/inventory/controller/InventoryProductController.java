package com.Gisma.OrderProduct.inventory.controller;

import com.Gisma.OrderProduct.inventory.dto.InventoryProductDTO;
import com.Gisma.OrderProduct.inventory.service.InventoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/InventoryProduct")
@RequiredArgsConstructor
public class InventoryProductController {

    private final InventoryProductService inventoryProductService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody InventoryProductDTO inventoryProductDTO){
        inventoryProductService.create(inventoryProductDTO);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryProductDTO update(@PathVariable Long id, @RequestBody InventoryProductDTO inventoryProductDTO) {
        return inventoryProductService.update(id,inventoryProductDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        inventoryProductService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryProductDTO> findAll() {
        return inventoryProductService.findAll();
    }


}
