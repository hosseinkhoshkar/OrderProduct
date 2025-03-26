package com.Gisma.OrderProduct.product.repository;

import com.Gisma.OrderProduct.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
