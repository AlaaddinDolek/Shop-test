package com.shop.shopapi.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.shop.shopapi.persistence.model.Order;
import com.shop.shopapi.persistence.model.OrderDetails;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {

    Optional<OrderDetails> findByOrder(Order order);

}
