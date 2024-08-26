package org.example.springbootonlineshoppingapplication.shopping.customer.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.springbootonlineshoppingapplication.shopping.cart.dto.Cart;
import org.example.springbootonlineshoppingapplication.shopping.order.dto.Order;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    private int customerId;
    private String customerName;
    private String emailAddress;
    private long phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_cart",referencedColumnName = "cartId")
            @JsonManagedReference
    Cart cart;
    @OneToOne(cascade = CascadeType.ALL)
    Order order;
}
