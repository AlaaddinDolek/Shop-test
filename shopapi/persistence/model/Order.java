package com.shop.shopapi.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.shop.shopapi.persistence.model.dtos.OrderDto;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cart cartOrdered;

    private Double finalPrice;
    private Integer totalItem;
    private Boolean isCouponUsed;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public OrderDto toOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setFinalPrice(finalPrice);
        orderDto.setIsCouponUsed(isCouponUsed);
        orderDto.setStatus(status);
        orderDto.setTotalItem(totalItem);
        orderDto.setUsername(user.getUsername());

        return orderDto;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCartOrdered() {
        return cartOrdered;
    }

    public void setCartOrdered(Cart cartOrdered) {
        this.cartOrdered = cartOrdered;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Boolean getIsCouponUsed() {
        return isCouponUsed;
    }

    public void setIsCouponUsed(Boolean isCouponUsed) {
        this.isCouponUsed = isCouponUsed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
