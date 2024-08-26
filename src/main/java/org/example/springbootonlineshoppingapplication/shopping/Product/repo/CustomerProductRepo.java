package org.example.springbootonlineshoppingapplication.shopping.Product.repo;

import org.example.springbootonlineshoppingapplication.shopping.Product.dto.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProductRepo extends JpaRepository<CustomerProduct, Integer> {
}
