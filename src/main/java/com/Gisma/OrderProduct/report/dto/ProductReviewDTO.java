package com.Gisma.OrderProduct.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewDTO {
    private String id;
    private Long productId;
    private String customerName;
    private String reviewText;
    private LocalDateTime reviewDate;
    private Double rating;
}
