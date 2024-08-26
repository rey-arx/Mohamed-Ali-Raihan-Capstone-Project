package org.example.springbootonlineshoppingapplication.shopping.cart.service;

import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.Cart;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.example.springbootonlineshoppingapplication.shopping.cart.repo.CartRepo;
import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.example.springbootonlineshoppingapplication.shopping.customer.exception.CustomerNotFoundException;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepo cartRepo;

    @Override
    public Cart addCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public void removeCart(int customerId) throws CustomerNotFoundException {
         cartRepo.removeCart(customerId);
    }

    @Override
    public Cart updateCart(Cart cart) {
        return null;
    }

    @Override
    public List<LineItem> getCart(int customerId) throws CustomerNotFoundException {
        return cartRepo.getCart(customerId);
    }
    @Override
    public Customer addToCart(int customerId, int productId) throws CustomerNotFoundException {
        cartRepo.addToCart(customerId,productId);
        return null;
    }

    @Override
    public void deleteCartItem(int productId,int customerId) throws CustomerNotFoundException {
        cartRepo.deleteCartItem(productId, customerId);
    }

    @Override
    public Order orderFromCart(int customerId) throws ProductOutOfStockException, CustomerNotFoundException  {
        return cartRepo.orderFromCart(customerId);
    }
}
