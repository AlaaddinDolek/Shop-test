package com.shop.shopapi.persistence.model.dtos;

public class OrderDto {

    private String username;
    private String status;
    private Double finalPrice;
    private Boolean isCouponUsed;
    private Integer totalItem;

    public OrderDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Boolean getIsCouponUsed() {
        return isCouponUsed;
    }

    public void setIsCouponUsed(Boolean isCouponUsed) {
        this.isCouponUsed = isCouponUsed;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }
}
