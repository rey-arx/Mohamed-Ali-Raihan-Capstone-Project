package org.example.springbootonlineshoppingapplication.shopping.order.dto;

import jakarta.persistence.*;
import lombok.*;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderLineItem {
    @Id
    @GeneratedValue
    private int orderLineItemId;
    private int orderProductId;
    private int orderProductQuantity;
    @ManyToOne
    Product product;
    private int orderProductPrice;
    private int orderProductTotal;
    private boolean orderStatus;
}
