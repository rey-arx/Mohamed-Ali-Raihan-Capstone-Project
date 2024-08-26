package org.example.springbootonlineshoppingapplication.shopping.cart.repo;

import org.example.springbootonlineshoppingapplication.shopping.cart.dao.CartDao;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> , CartDao {

}
