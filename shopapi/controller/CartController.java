package com.shop.shopapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.shopapi.exceptions.CartIsEmptyException;
import com.shop.shopapi.exceptions.CouponHasBeenUsedBeforeException;
import com.shop.shopapi.exceptions.CouponHasRunOutException;
import com.shop.shopapi.exceptions.CouponNotFoundException;
import com.shop.shopapi.exceptions.ProductAlreadyExistsInTheCartException;
import com.shop.shopapi.exceptions.ProductNotFoundException;
import com.shop.shopapi.exceptions.UserNotFoundException;
import com.shop.shopapi.persistence.model.dtos.CartDto;
import com.shop.shopapi.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired(required = false)
    private CartService cartService;

    @PostMapping
    public Long addToCart(@RequestParam Long userId, Long productId, Integer productCount)
            throws UserNotFoundException, ProductNotFoundException, ProductAlreadyExistsInTheCartException {
        return cartService.addToCart(userId, productId, productCount);
    }

    @GetMapping
    public List<CartDto> getCarts() {
        return cartService.getCarts();
    }

    @GetMapping("/remove")
    public CartDto removeFromCart(@RequestParam Long userId, Long productId)
            throws UserNotFoundException, ProductNotFoundException {
        return cartService.removeFromCart(userId, productId);
    }

    @GetMapping("/use_coupon")
    public CartDto useCoupon(@RequestParam Integer couponCode, Long userId) throws CouponHasBeenUsedBeforeException,
            UserNotFoundException, CouponHasRunOutException, CouponNotFoundException, CartIsEmptyException {
        return cartService.useCoupon(couponCode, userId);
    }
}
