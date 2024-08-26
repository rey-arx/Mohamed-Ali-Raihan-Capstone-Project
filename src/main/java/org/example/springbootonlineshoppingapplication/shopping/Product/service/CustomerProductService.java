package org.example.springbootonlineshoppingapplication.shopping.Product.service;

import org.example.springbootonlineshoppingapplication.shopping.Product.dto.CustomerProduct;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;

import java.util.List;

public interface CustomerProductService {

    public CustomerProduct createCustomerProduct(CustomerProduct customerProduct);

    public CustomerProduct updateCustomerProduct(CustomerProduct CustomerProduct);
    public void deleteCustomerProduct(CustomerProduct CustomerProduct);
    public List<CustomerProduct> getAllCustomerProducts();
    public CustomerProduct getCustomerProductByCustomerProductId(int id);

}
