package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // Autowire the CustomerService with Constructor-based dependency injection
    // https://blog.marcnuri.com/field-injection-is-not-recommended/
    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ADd mapping for GET /customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        // Delegate to the get the Customer Service
        return customerService.getCustomers();
    }
}
