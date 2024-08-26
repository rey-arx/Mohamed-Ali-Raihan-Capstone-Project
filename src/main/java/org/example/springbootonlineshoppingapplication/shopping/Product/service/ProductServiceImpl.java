package org.example.springbootonlineshoppingapplication.shopping.Product.service;

import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;
import org.example.springbootonlineshoppingapplication.shopping.Product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepo productRepo;
    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }
    @Override
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }
    @Override
    public void deleteProduct(Product product) {
        productRepo.delete(product);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    @Override
    public Product getProductByProductId(int productId) {
        return productRepo.findById(productId).get();
    }
}
