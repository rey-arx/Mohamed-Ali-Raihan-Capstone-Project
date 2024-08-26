package org.example.springbootonlineshoppingapplication.shopping.order.service;

import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.example.springbootonlineshoppingapplication.shopping.order.dao.OrderDao;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;
import org.example.springbootonlineshoppingapplication.shopping.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public Order addOrder(int customerId,int lineItemId) throws ProductOutOfStockException {
        return orderRepo.addOrder(customerId,lineItemId);
    }

    @Override
    public void addOrderFromProduct(int customerId,int productId) throws ProductOutOfStockException {
         orderRepo.addOrderFromProduct(customerId,productId);
    }
    @Override
    public void deleteProductFromOrder(int customerId, int productId){
        orderRepo.deleteProductFromOrder(customerId,productId);
    }
    @Override
    public void cancelOrder(int customerId, int productId){
        orderRepo.cancelOrder(customerId,productId);
    }

    @Override
    public Order displayOrder(int customerId) {
        return orderRepo.displayOrder(customerId);
    }
}
