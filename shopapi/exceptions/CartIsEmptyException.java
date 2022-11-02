package com.shop.shopapi.exceptions;

public class CartIsEmptyException extends Exception {
    public CartIsEmptyException(String message) {
        super(message);
    }
}
