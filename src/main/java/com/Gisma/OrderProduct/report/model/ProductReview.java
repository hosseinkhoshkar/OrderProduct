package com.Gisma.OrderProduct.report.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "product_reviews")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductReview {
    @Id
    private String id;
    private Long productId;
    private String customerName;
    private String reviewText;
    private LocalDateTime reviewDate;
    private Double rating;
}