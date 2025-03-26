package com.Gisma.OrderProduct.product.service;

import com.Gisma.OrderProduct.product.dto.ProductDTO;
import com.Gisma.OrderProduct.product.model.Product;
import com.Gisma.OrderProduct.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void create(ProductDTO productDTO) {

        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} created",product.getId());
    }

    public ProductDTO update(Long id , ProductDTO productDTO){

        Product product = Product.builder()
                .id(id)
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} updated",product.getId());

        return productDTO;
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public ProductDTO findById(Long id){

        Optional<Product> productOptional = productRepository.findById(id);
        Product product =productOptional.orElseThrow(() ->
                new EntityNotFoundException("Product not found with ID: " + id));

        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductDTO mapToProductResponse(Product product) {

        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
