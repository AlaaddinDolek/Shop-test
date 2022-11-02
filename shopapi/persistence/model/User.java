package com.shop.shopapi.persistence.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.shop.shopapi.persistence.model.dtos.UserDto;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Where(clause = "status='NotOrdered'")
    private Cart cart;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Order> orders;

    public UserDto toUserDto() {
        UserDto userDto = new UserDto();

        userDto.setName(name);
        userDto.setSurname(surname);
        userDto.setUsername(username);
        if (cart == null) {
        } else {
            userDto.setCart(cart.toCartDto());
        }
        for (Order order : orders) {
            if (userDto.getOrderIds() == null) {
                List<Long> orderIdList = new ArrayList<>();
                orderIdList.add(order.getId());
                userDto.setOrderIds(orderIdList);
                userDto.setOrderCount(1);
            } else {
                userDto.getOrderIds().add(order.getId());
                userDto.setOrderCount(userDto.getOrderCount() + 1);
            }
        }

        return userDto;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}
