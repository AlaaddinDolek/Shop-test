package com.shop.shopapi.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.shopapi.persistence.model.dtos.ProductDto;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private Double price;
    private String discription;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Coupon coupon;

    private Double priceAfterCoupon;

    private String seller;

    public ProductDto toProductDto() {
        ProductDto productDto = new ProductDto();

        productDto.setDiscription(discription);
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setSeller(seller);
        productDto.setType(type);
        if (coupon != null) {
            productDto.setCoupon(coupon.getCouponName());
            productDto.setPriceAfterCoupon(priceAfterCoupon);
        }

        return productDto;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

}