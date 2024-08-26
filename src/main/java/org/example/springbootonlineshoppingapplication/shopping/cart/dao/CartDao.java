package org.example.springbootonlineshoppingapplication.shopping.cart.dao;

import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.Cart;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.example.springbootonlineshoppingapplication.shopping.customer.exception.CustomerNotFoundException;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;

import java.util.List;

public interface CartDao {
    public Customer addToCart(int customerId,int productId) throws CustomerNotFoundException;
    public void removeFromCart(Customer customer,int productId);
//    public Cart displayCart(Customer customer);
public void deleteCartItem(int customerId ,int productId) throws CustomerNotFoundException;
    public List<LineItem> getCart(int customerId) throws CustomerNotFoundException;
    public void removeCart(int customerId) throws CustomerNotFoundException;
    public Order orderFromCart(int customerId) throws ProductOutOfStockException, CustomerNotFoundException;
}
