package com.Gisma.OrderProduct.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String customerName;
    private String customerEmail;
    private Double totalAmount;
    private List<OrderItemDTO> orderItems;
}
