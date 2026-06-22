package com.rajee.ecommerce_order_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rajee.ecommerce_order_service.entity.Product;
import com.rajee.ecommerce_order_service.repository.ProductRepository;

@SpringBootApplication
public class EcommerceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceOrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner test(ProductRepository repository) {

		return args -> {
			Product product = new Product();
			product.setName("Laptop");
			product.setPrice(1200.00);

			repository.save(product);

			System.out.println("Product inserted successfully");
		};
	}

}
