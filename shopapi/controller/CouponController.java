package com.shop.shopapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.shopapi.persistence.model.dtos.CouponDto;
import com.shop.shopapi.service.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    CouponService couponService;

    @PostMapping
    public Long createCoupon(@RequestBody CouponDto couponDto) {
        return couponService.createCoupon(couponDto);
    }

    @GetMapping
    public List<CouponDto> getAllCoupons() {
        return couponService.getAllCoupons();
    }

}
