package com.Gisma.OrderProduct.inventory.service;

import com.Gisma.OrderProduct.inventory.dto.InventoryDTO;
import com.Gisma.OrderProduct.inventory.model.Inventory;
import com.Gisma.OrderProduct.inventory.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void create(InventoryDTO inventoryDTO) {

        Inventory inventory = Inventory.builder()
                .code(inventoryDTO.getCode())
                .name(inventoryDTO.getName())
                .build();

        inventoryRepository.save(inventory);
        log.info("inventory {} created",inventory.getId());
    }

    public InventoryDTO update(Long id , InventoryDTO inventoryDTO){

        Inventory inventory = Inventory.builder()
                .id(id)
                .code(inventoryDTO.getCode())
                .name(inventoryDTO.getName())
                .build();

        inventoryRepository.save(inventory);
        log.info("inventory {} updated",inventory.getId());

        return inventoryDTO;
    }

    public void delete(Long id){
        inventoryRepository.deleteById(id);
    }

    public InventoryDTO findById(Long id){

        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        Inventory inventory =optionalInventory.orElseThrow(() ->
                new EntityNotFoundException("Inventory not found with ID: " + id));

        return InventoryDTO.builder()
                .code(inventory.getCode())
                .name(inventory.getName())
                .build();
    }

    public List<InventoryDTO> findAll() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(this::mapToInventoryResponse).toList();
    }

    private InventoryDTO mapToInventoryResponse(Inventory inventory) {

        return InventoryDTO.builder()
                .code(inventory.getCode())
                .name(inventory.getName())
                .build();
    }

}
