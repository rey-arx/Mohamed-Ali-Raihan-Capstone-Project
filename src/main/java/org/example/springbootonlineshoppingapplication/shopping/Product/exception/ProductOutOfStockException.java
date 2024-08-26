package org.example.springbootonlineshoppingapplication.shopping.Product.exception;

public class ProductOutOfStockException extends Exception{
    private int prodCode;

    public ProductOutOfStockException(int productId) {
        this.prodCode = productId;
    }
    public ProductOutOfStockException(){

    }
    public int getProdCode() {
        return prodCode;
    }

    public void setProdCode(int prodCode) {
        this.prodCode = prodCode;
    }

    @Override
    public String toString() {
        return "The Current Product " + prodCode +"Is Out Of Stock";
    }
}
