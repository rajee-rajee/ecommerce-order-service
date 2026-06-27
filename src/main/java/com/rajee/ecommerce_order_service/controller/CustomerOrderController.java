package com.rajee.ecommerce_order_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajee.ecommerce_order_service.entity.CustomerOrder;
import com.rajee.ecommerce_order_service.service.CustomerOrderService;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;
    
    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public CustomerOrder createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        return customerOrderService.createCustomerOrder(customerOrder);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public CustomerOrder getCustomerOrderById(@PathVariable Long id) {
        return customerOrderService.getCustomerOrderById(id);
    }
}
