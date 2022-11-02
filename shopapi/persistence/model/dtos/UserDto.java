package com.shop.shopapi.persistence.model.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserDto {

    private String username;
    private String name;
    private String surname;
    private Integer orderCount = 0;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Long> orderIds;
    private CartDto cart;

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }

}