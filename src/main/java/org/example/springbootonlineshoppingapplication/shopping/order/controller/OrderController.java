package org.example.springbootonlineshoppingapplication.shopping.order.controller;

import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;
import org.example.springbootonlineshoppingapplication.shopping.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public Order displayOrder(@RequestParam("customerId") int customerId){
        return orderService.displayOrder(customerId);
    }

    @PostMapping("/add")
    public Order addOrder(@RequestParam("customerId") int customerId, @RequestParam("lineItemId") int lineItemId) throws ProductOutOfStockException {
        return orderService.addOrder(customerId, lineItemId);
    }

    @PostMapping("/addbyproduct")
    public void addOrderFromProduct(@RequestParam("customerId") int customerId, @RequestParam("productId") int productId) throws ProductOutOfStockException {
        orderService.addOrderFromProduct(customerId, productId);
    }

    @DeleteMapping
    public void deleteProductFromOrder(@RequestParam("customerId") int customerId, @RequestParam("productId") int productId) {
        orderService.deleteProductFromOrder(customerId, productId);
    }
    @DeleteMapping("/cancel")
    public void cancelOrder(@RequestParam("customerId") int customerId, @RequestParam("productId") int productId) {
        orderService.cancelOrder(customerId,productId);
    }
}
