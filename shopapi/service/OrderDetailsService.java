package com.shop.shopapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.shopapi.exceptions.OrderNotFoundException;
import com.shop.shopapi.persistence.model.Order;
import com.shop.shopapi.persistence.model.OrderDetails;
import com.shop.shopapi.persistence.model.dtos.OrderDetailsDto;
import com.shop.shopapi.persistence.repository.OrderDetailsRepository;
import com.shop.shopapi.persistence.repository.OrderRepository;

@Service
public class OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrderRepository orderRepository;

    public OrderDetailsDto getOrderDetailsByOrderId(Long id) throws OrderNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent())
            throw new OrderNotFoundException("Order not found");

        Order order = optionalOrder.get();

        Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findByOrder(order);

        return optionalOrderDetails.get().tOrderDetailsDto();
    }

}
