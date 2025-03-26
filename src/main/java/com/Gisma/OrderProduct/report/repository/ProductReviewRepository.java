package com.Gisma.OrderProduct.report.repository;

import com.Gisma.OrderProduct.report.model.ProductReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductReviewRepository extends MongoRepository<ProductReview, String> {
    List<ProductReview> findByProductId(Long productId);
}