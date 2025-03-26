package com.Gisma.OrderProduct.inventory.service;

import com.Gisma.OrderProduct.inventory.dto.InventoryProductDTO;
import com.Gisma.OrderProduct.inventory.model.Inventory;
import com.Gisma.OrderProduct.inventory.model.InventoryProduct;
import com.Gisma.OrderProduct.inventory.repository.InventoryProductRepository;
import com.Gisma.OrderProduct.inventory.repository.InventoryRepository;
import com.Gisma.OrderProduct.product.model.Product;
import com.Gisma.OrderProduct.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryProductService {

    private final InventoryProductRepository inventoryProductRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public void create(InventoryProductDTO inventoryProductDTO) {

        InventoryProduct inventoryProduct = InventoryProduct.builder()
                .inventory(getInventoryById(inventoryProductDTO.getInventoryId()))
                .product(getProductById(inventoryProductDTO.getProductId()))
                .stock(inventoryProductDTO.getStock())
                .build();

        inventoryProductRepository.save(inventoryProduct);
        log.info("inventoryProduct {} created",inventoryProduct.getId());
    }

    public InventoryProductDTO update(Long id, InventoryProductDTO inventoryProductDTO) {

        InventoryProduct inventoryProduct = InventoryProduct.builder()
                .id(id)
                .inventory(getInventoryById(inventoryProductDTO.getInventoryId()))
                .product(getProductById(inventoryProductDTO.getProductId()))
                .stock(inventoryProductDTO.getStock())
                .build();

        inventoryProductRepository.save(inventoryProduct);
        log.info("inventoryProduct {} updated",inventoryProduct.getId());

        return inventoryProductDTO;
    }

    public void delete(Long id){
        inventoryProductRepository.deleteById(id);
    }

    private Inventory getInventoryById(Long id){
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        Inventory inventory = optionalInventory.orElseThrow(() ->
                new EntityNotFoundException("inventory not found with ID: " + id));
        return inventory;
    }

    private Product getProductById(Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        Product product =productOptional.orElseThrow(() ->
                new EntityNotFoundException("Product not found with ID: " + id));
        return product;
    }

    public List<InventoryProductDTO> findAll() {
        return inventoryProductRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList(); // Convert entities to DTOs
    }

    private InventoryProductDTO mapToDTO(InventoryProduct inventoryProduct) {
        return InventoryProductDTO.builder()
                .productId(inventoryProduct.getProduct().getId())
                .inventoryId(inventoryProduct.getInventory().getId())
                .stock(inventoryProduct.getStock())
                .build();
    }
}
