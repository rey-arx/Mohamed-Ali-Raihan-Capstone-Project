package org.example.springbootonlineshoppingapplication.shopping.cart.dto;

import jakarta.persistence.*;
import lombok.*;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LineItem {
    @Id
    @GeneratedValue
    private int lineItemId;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Product product;
    private int productQuantity;
    private int productPrice;
    private int cartId;
    private int productTotal;
    @ManyToOne
    Cart cart;

}
