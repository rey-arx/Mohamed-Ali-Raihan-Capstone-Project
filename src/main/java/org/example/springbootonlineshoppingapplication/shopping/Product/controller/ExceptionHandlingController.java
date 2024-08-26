package org.example.springbootonlineshoppingapplication.shopping.Product.controller;

import org.example.springbootonlineshoppingapplication.shopping.Product.dto.ErrorResponse;
import org.example.springbootonlineshoppingapplication.shopping.Product.exception.ProductOutOfStockException;
import org.example.springbootonlineshoppingapplication.shopping.customer.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String,String> invalidInputHandler(MethodArgumentNotValidException e){
    Map<String, String> errorMap = new HashMap<>();
    e.getBindingResult()
            .getFieldErrors()
            .forEach(error -> {
                errorMap.put(error.getCode(),error.getDefaultMessage());
            });
    return errorMap;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProductOutOfStockException.class)
    public ErrorResponse handleProductNotFound(ProductOutOfStockException e){
        return new ErrorResponse(500,e.toString());
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResponse handleProductNotFound(CustomerNotFoundException e){
        return new ErrorResponse(500,e.toString());
    }
}
