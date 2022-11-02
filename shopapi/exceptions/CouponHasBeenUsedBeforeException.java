package com.shop.shopapi.exceptions;

public class CouponHasBeenUsedBeforeException extends Exception {
    public CouponHasBeenUsedBeforeException(String message) {
        super(message);
    }
}
