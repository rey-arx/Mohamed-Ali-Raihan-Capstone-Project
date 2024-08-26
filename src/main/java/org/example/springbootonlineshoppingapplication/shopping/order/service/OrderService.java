package org.example.springbootonlineshoppingapplication.shopping.order.service;

import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderService {
    public Order addOrder(int customerId,int lineItemId) throws ProductOutOfStockException;
    public void addOrderFromProduct(int customerId,int productId) throws ProductOutOfStockException;
    public void deleteProductFromOrder(int customerId, int productId);
    public void cancelOrder(int customerId, int productId);
    public Order displayOrder( int customerId);
}
