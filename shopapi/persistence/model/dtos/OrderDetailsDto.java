package com.shop.shopapi.persistence.model.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class OrderDetailsDto {

    private List<PurchaseItemDto> purchaseItemList;
    private Double totalPrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double totalPriceAfterCoupon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalProductCount;
    private String status;
    private String userName;
    private Boolean isCouponUsed = false;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String couponName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double profitDueToDiscount;

    public OrderDetailsDto() {
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPriceAfterCoupon() {
        return totalPriceAfterCoupon;
    }

    public void setTotalPriceAfterCoupon(Double totalPriceAfterCoupon) {
        this.totalPriceAfterCoupon = totalPriceAfterCoupon;
    }

    public Integer getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(Integer totalProductCount) {
        this.totalProductCount = totalProductCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getIsCouponUsed() {
        return isCouponUsed;
    }

    public void setIsCouponUsed(Boolean isCouponUsed) {
        this.isCouponUsed = isCouponUsed;
    }

    public Double getProfitDueToDiscount() {
        return profitDueToDiscount;
    }

    public void setProfitDueToDiscount(Double profitDueToDiscount) {
        this.profitDueToDiscount = profitDueToDiscount;
    }

    public List<PurchaseItemDto> getPurchaseItemList() {
        return purchaseItemList;
    }

    public void setPurchaseItemList(List<PurchaseItemDto> purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

}
