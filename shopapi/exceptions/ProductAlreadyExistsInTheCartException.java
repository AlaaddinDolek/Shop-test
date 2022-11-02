package com.shop.shopapi.exceptions;

public class ProductAlreadyExistsInTheCartException extends Exception {
    public ProductAlreadyExistsInTheCartException(String message) {
        super(message);
    }

}
