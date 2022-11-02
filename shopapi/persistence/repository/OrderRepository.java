package com.shop.shopapi.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.shop.shopapi.persistence.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();

    Optional<Order> findById(Long id);

}
