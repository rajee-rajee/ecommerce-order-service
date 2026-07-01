package com.rajee.ecommerce_order_service.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajee.ecommerce_order_service.entity.Product;
import com.rajee.ecommerce_order_service.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Tag(name = "Product APIs")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "create a new product", description = "This endpoint allows an admin to create a new product.")
    public Product createProduct(@RequestBody @Valid Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @Operation(summary = "get all products", description = "This endpoint allows an admin or customer to retrieve all products.")
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get product by id", description = "This endpoint allows an admin or customer to retrieve a product by its ID.")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductID(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "update product by id", description = "This endpoint allows an admin to update a product by its ID.")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "delete product by id", description = "This endpoint allows an admin to delete a product by its ID.")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}
