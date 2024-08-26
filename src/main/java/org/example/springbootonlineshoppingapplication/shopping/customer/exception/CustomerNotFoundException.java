package org.example.springbootonlineshoppingapplication.shopping.customer.exception;

public class CustomerNotFoundException extends Exception{
    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public CustomerNotFoundException(){

    }
    public CustomerNotFoundException(int customerId){
        this.customerId =customerId;
    }

    @Override
    public String toString() {
        return "The Customer ID " + customerId +" is Not Found";
    }
}
