package org.example.springbootonlineshoppingapplication.shopping.Product.service;

import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Product product);
    public List<Product> getAllProducts();
    public Product getProductByProductId(int id);

}
