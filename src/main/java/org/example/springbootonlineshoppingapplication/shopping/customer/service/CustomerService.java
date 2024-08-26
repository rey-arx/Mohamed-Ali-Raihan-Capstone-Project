package org.example.springbootonlineshoppingapplication.shopping.customer.service;

import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.example.springbootonlineshoppingapplication.shopping.customer.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer findCustomerById(int id) throws CustomerNotFoundException;
    public List<Customer> findAllCustomers();
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);

}
