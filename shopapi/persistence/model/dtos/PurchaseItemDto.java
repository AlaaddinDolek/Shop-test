package com.shop.shopapi.persistence.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PurchaseItemDto {

    private String productName;
    private String productType;
    private Integer productCount;
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String couponName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double priceAfterCoupon;
    private String seller;

    public PurchaseItemDto() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Double getPriceAfterCoupon() {
        return priceAfterCoupon;
    }

    public void setPriceAfterCoupon(Double priceAfterCoupon) {
        this.priceAfterCoupon = priceAfterCoupon;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
