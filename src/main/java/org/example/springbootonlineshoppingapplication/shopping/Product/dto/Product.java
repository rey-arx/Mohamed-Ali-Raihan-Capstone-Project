package org.example.springbootonlineshoppingapplication.shopping.Product.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    private int productId;
    private String productName;
    private int productPrice;
    private int productQuantity;
    private String productCategory;
//    private boolean productStatus;

}
