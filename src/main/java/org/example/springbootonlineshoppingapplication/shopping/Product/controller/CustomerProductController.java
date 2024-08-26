package org.example.springbootonlineshoppingapplication.shopping.Product.controller;

import org.example.springbootonlineshoppingapplication.shopping.Product.dto.CustomerProduct;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;
import org.example.springbootonlineshoppingapplication.shopping.Product.service.CustomerProductService;
import org.example.springbootonlineshoppingapplication.shopping.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/customerProduct")
public class CustomerProductController {
    @Autowired
    private CustomerProductService customerProductService;

    @PostMapping()
    public CustomerProduct createCustomerProduct
            (@RequestBody CustomerProduct customerProduct){
        return customerProductService.createCustomerProduct(customerProduct);
    }
    @PutMapping()
    public CustomerProduct updateCustomerProduct
            (@RequestBody CustomerProduct customerproduct){
        return customerProductService.updateCustomerProduct(customerproduct);
    }
    @DeleteMapping
    public void deleteCustomerProduct
            (@RequestBody CustomerProduct customerproduct){
        customerProductService.deleteCustomerProduct(customerproduct);
    }
    @GetMapping()
    public List<CustomerProduct> getAllCustomerProducts(){
        return customerProductService.getAllCustomerProducts();
    }
    @GetMapping("/{CustomerProductId}")
    public CustomerProduct getCustomerProductByCustomerProductId
            (@PathVariable("CustomerProductId") int CustomerProductId){
        return customerProductService.getCustomerProductByCustomerProductId(CustomerProductId);
    }
}
