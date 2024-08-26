package org.example.springbootonlineshoppingapplication.shopping.order.dto;

import jakarta.persistence.*;
import lombok.*;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.LineItem;
import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private int orderId;
    private int customerId;
    private int total;
    @OneToMany
    List<OrderLineItem> orderLineItemList;
}
