package com.shop.shopapi.exceptions;

public class ProductAlreadyAvailableException extends Exception {
    public ProductAlreadyAvailableException(String message) {
        super(message);
    }
}
