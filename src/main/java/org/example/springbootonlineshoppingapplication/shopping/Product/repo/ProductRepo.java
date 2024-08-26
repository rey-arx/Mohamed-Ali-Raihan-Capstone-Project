package org.example.springbootonlineshoppingapplication.shopping.Product.repo;

import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
