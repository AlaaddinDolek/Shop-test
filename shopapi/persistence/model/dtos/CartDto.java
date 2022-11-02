package com.shop.shopapi.persistence.model.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CartDto {

    private List<PurchaseItemDto> itemsInCart;
    private Double totalPrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double priceAfterCoupon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double profitDueToDiscount;
    private Integer totalProductCount;

    public CartDto() {
    }

    public List<PurchaseItemDto> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(List<PurchaseItemDto> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public Double getProfitDueToDiscount() {
        return profitDueToDiscount;
    }

    public void setProfitDueToDiscount(Double profitDueToDiscount) {
        this.profitDueToDiscount = profitDueToDiscount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getPriceAfterCoupon() {
        return priceAfterCoupon;
    }

    public void setPriceAfterCoupon(Double priceAfterCoupon) {
        this.priceAfterCoupon = priceAfterCoupon;
    }

    public Integer getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(Integer totalProductCount) {
        this.totalProductCount = totalProductCount;
    }
}
