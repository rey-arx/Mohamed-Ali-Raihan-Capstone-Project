package org.example.springbootonlineshoppingapplication.shopping.customer;

import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.example.springbootonlineshoppingapplication.shopping.customer.exception.CustomerNotFoundException;
import org.example.springbootonlineshoppingapplication.shopping.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
    @DeleteMapping
    public void deleteCustomer(@RequestBody Customer customer) {
        customerService.deleteCustomer(customer);
    }
    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
        return customerService.findCustomerById(customerId);
        }
}
