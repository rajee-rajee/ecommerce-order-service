package com.rajee.ecommerce_order_service.controller;

import org.springframework.web.bind.annotation.*;

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
    public CustomerOrder createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        return customerOrderService.createCustomerOrder(customerOrder);
    }

    @GetMapping("/{id}")
    public CustomerOrder getCustomerOrderById(@PathVariable Long id) {
        return customerOrderService.getCustomerOrderById(id);
    }
}
