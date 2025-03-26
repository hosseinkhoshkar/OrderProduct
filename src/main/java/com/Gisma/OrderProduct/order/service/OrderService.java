package com.Gisma.OrderProduct.order.service;

import com.Gisma.OrderProduct.inventory.model.InventoryProduct;
import com.Gisma.OrderProduct.inventory.repository.InventoryProductRepository;
import com.Gisma.OrderProduct.order.dto.OrderDTO;
import com.Gisma.OrderProduct.order.dto.OrderItemDTO;
import com.Gisma.OrderProduct.exception.InsufficientStockException;
import com.Gisma.OrderProduct.order.model.Order;
import com.Gisma.OrderProduct.order.model.OrderItem;
import com.Gisma.OrderProduct.order.repository.OrderRepository;
import com.Gisma.OrderProduct.product.model.Product;
import com.Gisma.OrderProduct.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final InventoryProductRepository inventoryProductRepository;

    public void createOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .customerName(orderDTO.getCustomerName())
                .customerEmail(orderDTO.getCustomerEmail())
                .orderDate(LocalDateTime.now())
                .totalAmount(orderDTO.getTotalAmount())
                .build();

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream().map(orderItemDTO -> {

            // check stuck
            InventoryProduct inventoryProduct = inventoryProductRepository.findByProductId(orderItemDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("No inventory found for product ID: " + orderItemDTO.getProductId()));

            if (inventoryProduct.getStock() < orderItemDTO.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product ID: " + orderItemDTO.getProductId());
            }

            // reduce stuck
            inventoryProduct.setStock(inventoryProduct.getStock() - orderItemDTO.getQuantity());
            inventoryProductRepository.save(inventoryProduct);

            Product product = productRepository.findById(orderItemDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + orderItemDTO.getProductId()));

            return OrderItem.builder()
                    .order(order)
                    .product(product)
                    .price(orderItemDTO.getPrice())
                    .quantity(orderItemDTO.getQuantity())
                    .build();

        }).toList();

        order.setOrderItems(orderItems);
        orderRepository.save(order);
        log.info("Order {} created", order.getId());
    }

    public List<OrderDTO> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToOrderDTO).toList();
    }

    private OrderDTO mapToOrderDTO(Order order) {
        List<OrderItemDTO> orderItemDTOS = order.getOrderItems().stream().map(orderItem -> OrderItemDTO.builder()
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build()).toList();

        return OrderDTO.builder()
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .totalAmount(order.getTotalAmount())
                .orderItems(orderItemDTOS)
                .build();
    }
}
