package com.shop.shopapi.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.shop.shopapi.persistence.model.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

    List<Coupon> findAll();

    Optional<Coupon> findCouponByCouponCode(Integer couponCode);
}
