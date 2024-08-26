package org.example.springbootonlineshoppingapplication.shopping.Product.controller;

import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;
import org.example.springbootonlineshoppingapplication.shopping.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    @PutMapping()
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }
    @DeleteMapping
    public void deleteProduct(@RequestBody Product product){
        productService.deleteProduct(product);
    }
    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{productId}")
    public Product getProductByProductId(@PathVariable("productId") int productId){
        return productService.getProductByProductId(productId);
    }
}
