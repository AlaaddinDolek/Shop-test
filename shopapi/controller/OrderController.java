package com.shop.shopapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.shopapi.exceptions.CartIsEmptyException;
import com.shop.shopapi.exceptions.OrderNotFoundException;
import com.shop.shopapi.exceptions.UserNotFoundException;
import com.shop.shopapi.persistence.model.dtos.OrderDetailsDto;
import com.shop.shopapi.persistence.model.dtos.OrderDto;
import com.shop.shopapi.service.OrderDetailsService;
import com.shop.shopapi.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @PostMapping
    public Long setOrder(Long userId) throws UserNotFoundException, CartIsEmptyException {
        return orderService.setOrder(userId);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("order_details")
    public OrderDetailsDto getOrderDetailsById(@RequestParam Long id) throws OrderNotFoundException {
        return orderDetailsService.getOrderDetailsByOrderId(id);
    }

}
