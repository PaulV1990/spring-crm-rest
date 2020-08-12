package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // Autowire the CustomerService with Constructor-based dependency injection
    // https://blog.marcnuri.com/field-injection-is-not-recommended/
    private final CustomerService customerService;

    @Autowired
    // When we have a class with multiple constructors, we need to explicitly add the @Autowired annotation to any one of the constructors so that Spring knows which constructor to use to inject the dependencies.
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Add mapping for GET /customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        // Delegate to the get all Customers Service
        return customerService.getCustomers();
    }

    // Add mapping for GET /customers/{customerId}
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        // Delegate to the get the Customer Service
        Customer theCustomer = customerService.getCustomer(customerId);

        if (theCustomer == null) {
            throw new CustomerNotFoundException("Customer ID not found! ID = " + customerId);
        }

        return theCustomer;
    }

    // Add mapping for POST /customers - add new customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {
        theCustomer.setId(0);
        // Uses Hibernate (DAO package) session.SaveOrUpdate -> if PrimaryKey is empty (null or 0) then INSER else UPDATE
        customerService.saveCustomer(theCustomer);
        return theCustomer;
    }

    // Add mapping for PUT /customers - update existing customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {
        // Uses Hibernate (DAO package) session.SaveOrUpdate -> if PrimaryKey is empty (null or 0) then INSER else UPDATE
        customerService.saveCustomer(theCustomer);
        return theCustomer;
    }
}