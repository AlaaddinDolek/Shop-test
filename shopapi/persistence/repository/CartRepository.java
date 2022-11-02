package com.shop.shopapi.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shop.shopapi.persistence.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Cart> findCartByStatus(String status);

    List<Cart> findAll();
}
