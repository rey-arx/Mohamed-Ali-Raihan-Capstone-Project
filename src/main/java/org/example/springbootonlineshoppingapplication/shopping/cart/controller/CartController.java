package org.example.springbootonlineshoppingapplication.shopping.cart.controller;


import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.Cart;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.example.springbootonlineshoppingapplication.shopping.cart.service.CartService;
import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.example.springbootonlineshoppingapplication.shopping.customer.exception.CustomerNotFoundException;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public List<LineItem> getCart(@PathVariable("customerId")
                                  int customerId) throws CustomerNotFoundException {
        return cartService.getCart(customerId);
    }

    @PostMapping
    public void addCart(@RequestBody Cart cart) {
        cartService.addCart(cart);
    }

    @PutMapping
    public void updateCart(@RequestBody Cart cart) {
        cartService.updateCart(cart);
    }

    @DeleteMapping("/{customerId}")
    public void removeCart(@PathVariable int customerId)
            throws CustomerNotFoundException {
        cartService.removeCart(customerId);
    }

    @DeleteMapping("/remove")
    public void deleteCartItem(@RequestParam int customerId,
                               @RequestParam int productId) throws CustomerNotFoundException {
        cartService.deleteCartItem(customerId, productId);
    }

    @PostMapping("/add")
    public Customer addToCart(@RequestParam("customerId") int customerId,
                              @RequestParam("productId") int productId) throws CustomerNotFoundException {
        return cartService.addToCart(customerId, productId);
    }

    @PostMapping("/order")
    public Order orderFromCart(@RequestParam("customerId")
                               int customerId) throws ProductOutOfStockException, CustomerNotFoundException {
        return cartService.orderFromCart(customerId);
    }

}
