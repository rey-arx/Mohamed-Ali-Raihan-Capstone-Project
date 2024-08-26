package org.example.springbootonlineshoppingapplication.shopping.Product.service;

import org.example.springbootonlineshoppingapplication.shopping.Product.dto.CustomerProduct;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;
import org.example.springbootonlineshoppingapplication.shopping.Product.repo.CustomerProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerProductServiceImpl implements CustomerProductService{
    @Autowired
    CustomerProductRepo customerProductRepo;

    @Override
    public CustomerProduct createCustomerProduct(CustomerProduct CustomerProduct) {
        return customerProductRepo.save(CustomerProduct);
    }

    @Override
    public CustomerProduct updateCustomerProduct(CustomerProduct CustomerProduct) {
        return customerProductRepo.save(CustomerProduct);
    }

    @Override
    public void deleteCustomerProduct(CustomerProduct CustomerProduct) {
        customerProductRepo.delete(CustomerProduct);
    }

    @Override
    public List<CustomerProduct> getAllCustomerProducts() {
        return customerProductRepo.findAll();
    }

    @Override
    public CustomerProduct getCustomerProductByCustomerProductId(int CustomerProductId) {
        return customerProductRepo.findById(CustomerProductId).get();
    }
}
