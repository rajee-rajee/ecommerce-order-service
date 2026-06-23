package com.rajee.ecommerce_order_service.service;

import com.rajee.ecommerce_order_service.entity.Product;
import com.rajee.ecommerce_order_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return  productRepository.findAll();
    }

    public Product getProductID(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
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
