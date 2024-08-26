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
public class CustomerProduct {
    @Id
    private int customerProductId;
    private String customerProductName;
    private int customerProductPrice;
    private int customerProductQuantity;
    private String customerProductCategory;
    private int customerQuantity;
}
