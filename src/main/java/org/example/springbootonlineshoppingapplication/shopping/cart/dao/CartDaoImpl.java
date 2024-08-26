package org.example.springbootonlineshoppingapplication.shopping.cart.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.CustomerProduct;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;
import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.Product.repo.ProductRepo;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.Cart;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.example.springbootonlineshoppingapplication.shopping.customer.exception.CustomerNotFoundException;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.OrderLineItem;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Customer addToCart(int customerId, int productId) throws CustomerNotFoundException {
//        Cart cart = em.find(Cart.class, cartId);
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) throw new CustomerNotFoundException(customerId);
        Cart cart = customer.getCart();
        Product p1 = em.find(Product.class, productId);
        if (cart == null) {
            cart = new Cart();
            cart.setCustomerId(customer.getCustomerId());
            cart.setCustomer(customer);
            List<LineItem> lineItemList = new ArrayList<>();
            LineItem lineItem = new LineItem();
            lineItem.setProduct(p1);
            lineItem.setCartId(cart.getCartId());
            lineItem.setProductQuantity(1);
            lineItem.setProductPrice(p1.getProductPrice());
            lineItem.setProductTotal(p1.getProductPrice());
            em.persist(lineItem);
            lineItemList.add(lineItem);
            cart.setLineItemList(lineItemList);
            customer.setCart(cart);

        } else {
            List<LineItem> lineItemList = cart.getLineItemList();
            boolean found = false;
            int lineItemId = 0;
            for (LineItem lineItem : lineItemList) {
                if (lineItem.getProduct() == p1) {
                    lineItemId = lineItem.getLineItemId();
                    found = true;
                }
            }
            if (found) {
                for (LineItem lineItem : lineItemList) {
                    if (lineItem.getLineItemId() == lineItemId) {
                        lineItem.setProductTotal(lineItem.getProductTotal() + p1.getProductPrice());
                        lineItem.setProductQuantity(lineItem.getProductQuantity() + 1);
                        break;
                    }
                }
            } else {
                LineItem lineItem = new LineItem();
                lineItem.setProduct(p1);
                lineItem.setCartId(cart.getCartId());
                lineItem.setProductQuantity(1);
                lineItem.setProductPrice(p1.getProductPrice());
                lineItem.setProductTotal(p1.getProductPrice());
                cart.getLineItemList().add(lineItem);
                em.persist(lineItem);
            }
        customer.setCart(cart);
        }
        em.persist(customer);
        return customer;
    }

    @Override
    public void removeFromCart(Customer customer, int productId) {
    }

    @Override
    @Transactional
    public void deleteCartItem(int customerId, int productId)
            throws CustomerNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) throw new CustomerNotFoundException(customerId);
        Product product = em.find(Product.class, productId);
        Cart cart = customer.getCart();
        List<LineItem> lineItemList = cart.getLineItemList();
        boolean found = false;
        int lineItemId = 0;
        for (LineItem lineItem : lineItemList) {
            if (lineItem.getProduct() == product) {
                lineItemId = lineItem.getLineItemId();
                found = true;
            if (lineItem.getProductQuantity() > 1) {
                lineItem.setProductQuantity(lineItem.getProductQuantity() - 1);
                lineItem.setProductTotal
            (lineItem.getProductTotal() - lineItem.getProductPrice());
            } else lineItemList.remove(lineItem);
            break;
            }
        }
        cart.setLineItemList(lineItemList);
        customer.setCart(cart);
        em.persist(customer);

    }

    @Override
    public List<LineItem> getCart(int customerId) throws CustomerNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) throw new CustomerNotFoundException(customerId);
        return customer.getCart().getLineItemList();
    }

    @Override
    @Transactional
    public void removeCart(int customerId) throws CustomerNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) throw new CustomerNotFoundException(customerId);
        customer.getCart().setLineItemList(null);
        em.persist(customer);
    }

    @Override
    @Transactional
    public Order orderFromCart(int customerId) throws ProductOutOfStockException, CustomerNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) throw new CustomerNotFoundException(customerId);
        List<LineItem> lineItemList = customer.getCart().getLineItemList();
//        Cart cart = customer.getCart();
        Order order = customer.getOrder();
        if (order == null) {
            order = new Order();
            int orderTotal = 0;
            order.setCustomerId(customerId);
            List<OrderLineItem> orderLineItemList = new ArrayList<>();
            for (LineItem lineItem : lineItemList) {
                Product product = em.find(Product.class, lineItem.getProduct().getProductId());
                if (product.getProductQuantity() < lineItem.getProductQuantity())
                    throw new ProductOutOfStockException(product.getProductId());
                order.setTotal(order.getTotal() + lineItem.getProductTotal());
                OrderLineItem orderLineItem = new OrderLineItem();
                orderLineItem.setProduct(lineItem.getProduct());
                orderLineItem.setOrderProductTotal(lineItem.getProductTotal());
                orderLineItem.setOrderProductPrice(lineItem.getProductPrice());
                orderLineItem.setOrderProductQuantity(lineItem.getProductQuantity());
                orderLineItem.setOrderProductId(lineItem.getProduct().getProductId());
                em.persist(orderLineItem);
                orderLineItemList.add(orderLineItem);
                product.setProductQuantity(product.getProductQuantity() - lineItem.getProductQuantity());
                em.persist(product);

            }
            order.setOrderLineItemList(orderLineItemList);
            em.persist(order);
            customer.setOrder(order);
            customer.getCart().setLineItemList(null);
            em.persist(customer);
        } else {
            List<OrderLineItem> orderLineItemList = order.getOrderLineItemList();
            for (LineItem lineItem : lineItemList) {
                Product product = em.find(Product.class, lineItem.getProduct().getProductId());
                if (product.getProductQuantity() < lineItem.getProductQuantity())
                    throw new ProductOutOfStockException(product.getProductId());
                order.setTotal(order.getTotal() + lineItem.getProductTotal());
                OrderLineItem orderLineItem = new OrderLineItem();
                orderLineItem.setProduct(lineItem.getProduct());
                orderLineItem.setOrderProductTotal(lineItem.getProductTotal());
                orderLineItem.setOrderProductPrice(lineItem.getProductPrice());
                orderLineItem.setOrderProductQuantity(lineItem.getProductQuantity());
                orderLineItem.setOrderProductId(lineItem.getProduct().getProductId());
                em.persist(orderLineItem);
                orderLineItemList.add(orderLineItem);
                product.setProductQuantity(product.getProductQuantity() - lineItem.getProductQuantity());
                em.persist(product);
            }
            order.setOrderLineItemList(orderLineItemList);
            em.persist(order);
            customer.setOrder(order);
            customer.getCart().setLineItemList(null);
            em.persist(customer);
//            Product product = em.find(Product.class,)

        }

        return order;
    }
}
