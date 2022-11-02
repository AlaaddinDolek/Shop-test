package com.shop.shopapi.persistence.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.shopapi.persistence.model.Product;

public class ProductDto {

    private String type;
    private String name;
    private Double price;
    private String discription;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String coupon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double priceAfterCoupon;
    private String seller;

    public Product toProductEntity() {
        Product product = new Product();
        product.setType(type);
        product.setName(name);
        product.setPrice(price);
        product.setSeller(seller);
        product.setDiscription(discription);

        return product;
    }

    public ProductDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
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
}
