package com.Gisma.OrderProduct.report.service;

import com.Gisma.OrderProduct.report.dto.ProductReviewDTO;
import com.Gisma.OrderProduct.report.model.ProductReview;
import com.Gisma.OrderProduct.report.repository.ProductReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;

    public void create(ProductReviewDTO productReviewDTO) {
        ProductReview productReview = mapToProductReview(productReviewDTO);
        productReviewRepository.save(productReview);
    }

    public ProductReviewDTO findById(String id) {
        ProductReview productReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductReview not found"));
        return mapToProductReviewDTO(productReview);
    }

    public List<ProductReviewDTO> findByProductId(Long productId) {
        List<ProductReview> reviews = productReviewRepository.findByProductId(productId);
        return reviews.stream().map(this::mapToProductReviewDTO).collect(Collectors.toList());
    }

    public List<ProductReviewDTO> findAll() {
        List<ProductReview> reviews = productReviewRepository.findAll();
        return reviews.stream().map(this::mapToProductReviewDTO).collect(Collectors.toList());
    }

    public ProductReviewDTO update(String id, ProductReviewDTO productReviewDTO) {
        ProductReview productReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductReview not found with ID: " + id));

        productReview.setReviewText(productReviewDTO.getReviewText());
        productReview.setRating(productReviewDTO.getRating());
        productReview.setCustomerName(productReviewDTO.getCustomerName());
        productReview.setReviewDate(productReviewDTO.getReviewDate());

        productReviewRepository.save(productReview);
        return mapToProductReviewDTO(productReview);
    }

    public void delete(String id) {
        productReviewRepository.deleteById(id);
    }

    private ProductReview mapToProductReview(ProductReviewDTO productReviewDTO) {
        return ProductReview.builder()
                .id(productReviewDTO.getId())
                .productId(productReviewDTO.getProductId())
                .customerName(productReviewDTO.getCustomerName())
                .reviewText(productReviewDTO.getReviewText())
                .reviewDate(productReviewDTO.getReviewDate())
                .rating(productReviewDTO.getRating())
                .build();
    }

    private ProductReviewDTO mapToProductReviewDTO(ProductReview productReview) {
        return ProductReviewDTO.builder()
                .id(productReview.getId())
                .productId(productReview.getProductId())
                .customerName(productReview.getCustomerName())
                .reviewText(productReview.getReviewText())
                .reviewDate(productReview.getReviewDate())
                .rating(productReview.getRating())
                .build();
    }
}
