package com.rajee.ecommerce_order_service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rajee.ecommerce_order_service.entity.Product;
import com.rajee.ecommerce_order_service.repository.ProductRepository;

@Service
public class ProductService {

    private static final Logger log =
        LoggerFactory.getLogger(ProductService.class);
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {

        log.info("Creating product: {}", product.getName());

        Product savedProduct = productRepository.save(product);

        log.info("Product created with id: {}", savedProduct.getId());

        return savedProduct;
    }

    public List<Product> getAllProduct() {

        log.info("Fetching all products");

        return productRepository.findAll();
    }

   public Product getProductID(Long productId) {

        log.info("Fetching product with id: {}", productId);

        return productRepository.findById(productId)
                .orElseThrow(() -> {

                    log.error("Product not found with id: {}", productId);

                    return new RuntimeException(
                            "Product not found with id: " + productId);
                });
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
