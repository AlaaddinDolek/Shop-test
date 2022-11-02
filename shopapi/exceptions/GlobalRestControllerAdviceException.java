package com.shop.shopapi.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdviceException {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails usernameAlreadyExists(UsernameAlreadyExistsException ex) {
        return new CustomErrorDetails(new Date(), "Username already exists !", ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails userNotFound(UserNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "User not found !", ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails productNotFound(ProductNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Product not found !", ex.getMessage());
    }

    @ExceptionHandler(CartIsEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails cartIsEmptyException(CartIsEmptyException ex) {
        return new CustomErrorDetails(new Date(), "Cart is empty !", ex.getMessage());
    }

    @ExceptionHandler(CouponNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails couponNotFoundException(CouponNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Coupon not found !", ex.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails orderNotFoundException(OrderNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Order not found !", ex.getMessage());
    }

    @ExceptionHandler(ProductAlreadyAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails productAlreadyAvailableException(ProductAlreadyAvailableException ex) {
        return new CustomErrorDetails(new Date(), "Product already available for the seller !", ex.getMessage());
    }

    @ExceptionHandler(SellerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails sellerNotFoundException(SellerNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Seller not found!", ex.getMessage());
    }

    @ExceptionHandler(CouponHasBeenUsedBeforeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails couponHasBeenUsedException(CouponHasBeenUsedBeforeException ex) {
        return new CustomErrorDetails(new Date(), "Coupon has been used before !", ex.getMessage());
    }

    @ExceptionHandler(ProductAlreadyExistsInTheCartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails productAlreadyExistsInTheCart(ProductAlreadyExistsInTheCartException ex) {
        return new CustomErrorDetails(new Date(), "Product already exists in the cart !", ex.getMessage());
    }

    @ExceptionHandler(CouponHasRunOutException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails couponHasRunOutException(CouponHasRunOutException ex) {
        return new CustomErrorDetails(new Date(), "Coupon has run out !", ex.getMessage());
    }

}