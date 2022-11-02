package com.shop.shopapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.shopapi.persistence.model.Coupon;
import com.shop.shopapi.persistence.model.dtos.CouponDto;
import com.shop.shopapi.persistence.repository.CouponRepository;

@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository;

    public Long createCoupon(CouponDto couponDto) {
        Coupon coupon = new Coupon();

        int random = (int) (Math.random() * 10000 + 1);
        coupon.setCouponCode(random);
        coupon.setCouponName(couponDto.getCouponName());
        coupon.setDiscountRate(couponDto.getDiscountRate());
        coupon.setDiscription(couponDto.getDiscription());
        coupon.setCouponCount(couponDto.getCouponCount());
        couponRepository.save(coupon);

        return couponRepository.save(coupon).getId();
    }

    public List<CouponDto> getAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        List<CouponDto> couponDtos = new ArrayList<>();
        for (Coupon coupon : coupons) {
            couponDtos.add(coupon.toCouponDto());
        }
        return couponDtos;
    }

}
