package com.shop.shopapi.exceptions;

public class CouponNotFoundException extends Exception {
    public CouponNotFoundException(String message) {
        super(message);
    }
}
