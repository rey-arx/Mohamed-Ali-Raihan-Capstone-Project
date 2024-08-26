package org.example.springbootonlineshoppingapplication.shopping.cart.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.CustomerProduct;
import org.example.springbootonlineshoppingapplication.shopping.Product.dto.Product;
import org.example.springbootonlineshoppingapplication.shopping.customer.dto.Customer;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue
    private int cartId;
    private int customerId;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//            @JoinColumn(name = "Cart_Line_Item_List")
    List<LineItem> lineItemList;
//    List<CustomerProduct> productList;
    @OneToOne(mappedBy = "cart")
            @JsonBackReference
    Customer customer;

}
