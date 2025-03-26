package com.Gisma.OrderProduct.inventory.model;

import com.Gisma.OrderProduct.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INVENTORY_PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "inventory", nullable = false)
    private Inventory inventory;

    private Integer stock;
}
