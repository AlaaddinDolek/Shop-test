package com.shop.shopapi.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shop.shopapi.persistence.model.dtos.CouponDto;

@Entity
@Table(name="coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String couponName;
    private String discription;
    private Integer discountRate;
    private Integer couponCode;
    private Integer couponCount;

    public CouponDto toCouponDto() {
        CouponDto couponDto = new CouponDto();
        couponDto.setCouponName(couponName);
        couponDto.setDiscountRate(discountRate);
        couponDto.setDiscription(discription);
        couponDto.setCouponCode(couponCode);

        return couponDto;
    }

    public Coupon(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCouponName() {
        return couponName;
    }
    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
    public String getDiscription() {
        return discription;
    }
    public void setDiscription(String discription) {
        this.discription = discription;
    }
    public Integer getDiscountRate() {
        return discountRate;
    }
    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }
    public Integer getCouponCode() {
        return couponCode;
    }
    public void setCouponCode(Integer couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

}
