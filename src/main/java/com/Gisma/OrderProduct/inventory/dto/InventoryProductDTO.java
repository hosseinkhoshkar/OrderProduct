package com.Gisma.OrderProduct.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryProductDTO {

    private Long productId;
    private Long inventoryId;
    private Integer stock;

}
