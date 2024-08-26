package org.example.springbootonlineshoppingapplication.shopping.Product.dto;


import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ErrorResponse {
    private int errorCode;
    private String message;

}
