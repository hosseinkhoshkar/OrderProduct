package com.Gisma.OrderProduct.inventory.repository;

import com.Gisma.OrderProduct.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
