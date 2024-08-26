package org.example.springbootonlineshoppingapplication.shopping.order.repo;

import org.aspectj.weaver.ast.Or;
import org.example.springbootonlineshoppingapplication.shopping.order.dao.OrderDao;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer>     , OrderDao {
}
