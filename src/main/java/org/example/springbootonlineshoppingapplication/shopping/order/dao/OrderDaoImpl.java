package org.example.springbootonlineshoppingapplication.shopping.order.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;
import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.Cart;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.OrderLineItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Order addOrder(int customerId, int lineItemId) throws ProductOutOfStockException {
        Customer customer = em.find(Customer.class, customerId);
        LineItem lineItem = em.find(LineItem.class, lineItemId);
        Product product = em.find(Product.class, lineItem.getProduct().getProductId());
        if (product.getProductQuantity() < lineItem.getProductQuantity())
            throw new ProductOutOfStockException(product.getProductId());
//        Order Order = customer.getOrder();
        Order order = customer.getOrder();
        if (order == null) {
            order = new Order();
            List<OrderLineItem> customerOrderLineItemList = new ArrayList<>();
            order.setCustomerId(customerId);
            order.setTotal(order.getTotal() + lineItem.getProductTotal());
            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setOrderProductId(lineItem.getProduct().getProductId());
            orderLineItem.setProduct(lineItem.getProduct());
            orderLineItem.setOrderProductQuantity(lineItem.getProductQuantity());
            orderLineItem.setOrderProductPrice(lineItem.getProductPrice());
            orderLineItem.setOrderProductTotal(lineItem.getProductTotal());
            orderLineItem.setOrderStatus(true);
            em.persist(orderLineItem);
            customerOrderLineItemList.add(orderLineItem);
            order.setOrderLineItemList(customerOrderLineItemList);
//            em.persist(order);
        } else {
            List<OrderLineItem> lineItemList = order.getOrderLineItemList();
            order.setTotal(order.getTotal() + lineItem.getProductTotal());
            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setOrderProductId(lineItem.getProduct().getProductId());
            orderLineItem.setOrderProductQuantity(lineItem.getProductQuantity());
            orderLineItem.setOrderProductPrice(lineItem.getProductPrice());
            orderLineItem.setProduct(lineItem.getProduct());
            orderLineItem.setOrderProductTotal(lineItem.getProductTotal());
            orderLineItem.setOrderStatus(true);
            em.persist(orderLineItem);
            order.getOrderLineItemList().add(orderLineItem);
        }
        customer.setOrder(order);
        Cart cart = customer.getCart();
        List<LineItem> lineItemList = cart.getLineItemList();
        lineItemList.remove(lineItem);
        customer.setCart(cart);
        em.persist(customer);
        product.setProductQuantity(product.getProductQuantity() - lineItem.getProductQuantity());
        em.persist(product);

        return order;
    }

    @Override
    @Transactional
    public void addOrderFromProduct(int customerId, int productId) throws ProductOutOfStockException {
        Customer customer = em.find(Customer.class, customerId);
        Product product = em.find(Product.class, productId);
        if (product.getProductQuantity() == 0) throw new ProductOutOfStockException(productId);
        Order order = customer.getOrder();

        if (order == null) {
            order = new Order();
            List<OrderLineItem> customerOrderLineItemList = new ArrayList<>();
            order.setCustomerId(customerId);
            order.setTotal(product.getProductPrice());
            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setOrderProductId(product.getProductId());
            orderLineItem.setOrderProductQuantity(1);
            orderLineItem.setProduct(product);
            orderLineItem.setOrderProductPrice(product.getProductPrice());
            orderLineItem.setOrderProductTotal(product.getProductPrice());
            orderLineItem.setOrderStatus(true);
            em.persist(orderLineItem);
//        List<OrderLineItem> custoemrOrderLineItemList = new ArrayList<>();
            customerOrderLineItemList.add(orderLineItem);
            order.setOrderLineItemList(customerOrderLineItemList);
        } else {
            List<OrderLineItem> orderLineItemList = order.getOrderLineItemList();
            order.setTotal(order.getTotal() + product.getProductPrice());
            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setOrderProductId(product.getProductId());
            orderLineItem.setOrderProductQuantity(1);
            orderLineItem.setProduct(product);
            orderLineItem.setOrderProductPrice(product.getProductPrice());
            orderLineItem.setOrderProductTotal(product.getProductPrice());
            orderLineItem.setOrderStatus(true);
            em.persist(orderLineItem);
            order.getOrderLineItemList().add(orderLineItem);
//            }

        }
        customer.setOrder(order);
        em.persist(customer);
        product.setProductQuantity(product.getProductQuantity() - 1);
        em.persist(product);
    }

    @Override
    @Transactional
    public void deleteProductFromOrder(int customerId, int productId) {
        Customer customer = em.find(Customer.class, customerId);
        Product product = em.find(Product.class, productId);
        Order order = customer.getOrder();
        List<OrderLineItem> orderLineItemList = order.getOrderLineItemList();
        for (OrderLineItem orderLineItem : orderLineItemList) {
            if (orderLineItem.getProduct().getProductId() == productId) {
                if (orderLineItem.getOrderProductQuantity() > 1) {
                    orderLineItem.setOrderProductTotal(orderLineItem.getOrderProductTotal() - product.getProductPrice());
                    orderLineItem.setOrderProductQuantity(orderLineItem.getOrderProductQuantity() - 1);
                } else {
                    orderLineItemList.remove(orderLineItem);
                }
                break;
            }
        }
        order.setTotal(order.getTotal() - product.getProductPrice());
        customer.setOrder(order);
        em.persist(customer);
        product.setProductQuantity(product.getProductQuantity() + 1);
        em.persist(product);
    }

    @Override
    @Transactional
    public void cancelOrder(int customerId, int productId) {
        Customer customer = em.find(Customer.class, customerId);
        Order order = customer.getOrder();
        Product product = em.find(Product.class, productId);
        List<OrderLineItem> lineItemList = order.getOrderLineItemList();
        for (OrderLineItem orderLineItem : lineItemList) {
            if (orderLineItem.getProduct().getProductId() == productId) {
                product.setProductQuantity(product.getProductQuantity()+ orderLineItem.getOrderProductQuantity());
                order.setTotal(order.getTotal() - orderLineItem.getOrderProductTotal());
                lineItemList.remove(orderLineItem);
                break;
            }
        }
        order.setOrderLineItemList(lineItemList);
        customer.setOrder(order);
        em.persist(customer);
        em.persist(product);
    }

    @Override
    @Transactional
    public Order displayOrder(int customerId) {
        Customer customer = em.find(Customer.class, customerId);
        return customer.getOrder();
//        return null;
    }

}

