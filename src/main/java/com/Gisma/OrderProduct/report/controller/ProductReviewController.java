package com.Gisma.OrderProduct.report.controller;

import com.Gisma.OrderProduct.report.dto.ProductReviewDTO;
import com.Gisma.OrderProduct.report.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product-reviews")
@RequiredArgsConstructor
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody ProductReviewDTO productReviewDTO) {
        productReviewService.create(productReviewDTO);
    }

    @GetMapping("/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductReviewDTO> getReviewsByProductId(@PathVariable Long productId) {
        return productReviewService.findByProductId(productId);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductReviewDTO getReviewById(@PathVariable String id) {
        return productReviewService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductReviewDTO> findAllReviews() {
        return productReviewService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductReviewDTO updateReview(@PathVariable String id, @RequestBody ProductReviewDTO productReviewDTO) {
        return productReviewService.update(id, productReviewDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable String id) {
        productReviewService.delete(id);
    }
}
