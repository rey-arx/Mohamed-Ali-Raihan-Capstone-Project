package org.example.springbootonlineshoppingapplication.shopping.cart.service;

import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.Cart;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.example.springbootonlineshoppingapplication.shopping.customer.exception.CustomerNotFoundException;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;

import java.util.List;

public interface CartService {
    public Cart addCart(Cart cart);
    public void removeCart(int customerId) throws CustomerNotFoundException;
    public Cart updateCart(Cart cart);
    public List<LineItem> getCart(int cartId) throws CustomerNotFoundException;
    public Customer addToCart(int customerId, int productId) throws CustomerNotFoundException ;
    public void deleteCartItem( int productId, int customerId) throws CustomerNotFoundException;
    public Order orderFromCart(int customerId) throws ProductOutOfStockException, CustomerNotFoundException;
}
