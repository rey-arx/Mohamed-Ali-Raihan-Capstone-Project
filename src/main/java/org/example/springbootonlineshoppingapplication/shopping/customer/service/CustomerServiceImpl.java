package org.example.springbootonlineshoppingapplication.shopping.customer.service;

import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.example.springbootonlineshoppingapplication.shopping.customer.exception.CustomerNotFoundException;
import org.example.springbootonlineshoppingapplication.shopping.customer.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public Customer findCustomerById(int id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepo.findById(id);
        if( customer.isEmpty()) throw new CustomerNotFoundException(id);
        else return customer.get();
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepo.delete(customer);
    }
}
