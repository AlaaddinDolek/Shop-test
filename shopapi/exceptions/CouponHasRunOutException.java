package com.shop.shopapi.exceptions;

public class CouponHasRunOutException extends Exception {
    public CouponHasRunOutException(String message) {
        super(message);
    }
}
