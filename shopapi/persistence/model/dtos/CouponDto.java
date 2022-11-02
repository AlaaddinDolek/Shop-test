package com.shop.shopapi.persistence.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CouponDto {

    private String couponName;
    private Integer discountRate;
    private String discription;
    private Integer couponCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer couponCount;

    public CouponDto() {
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
