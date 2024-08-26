package org.example.springbootonlineshoppingapplication.shopping.customer.repo;

import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
