package com.Gisma.OrderProduct.inventory.repository;

import com.Gisma.OrderProduct.inventory.model.InventoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryProductRepository extends JpaRepository<InventoryProduct, Long> {
    Optional<InventoryProduct> findByProductId(Long productId);
}
