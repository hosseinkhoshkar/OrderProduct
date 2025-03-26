package com.Gisma.OrderProduct.order.repository;

import com.Gisma.OrderProduct.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
