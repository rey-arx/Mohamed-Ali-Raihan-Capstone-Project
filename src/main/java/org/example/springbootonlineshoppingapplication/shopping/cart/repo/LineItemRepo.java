package org.example.springbootonlineshoppingapplication.shopping.cart.repo;

import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepo extends JpaRepository<LineItem,Integer> {

}
