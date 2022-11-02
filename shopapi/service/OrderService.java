package com.shop.shopapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.shopapi.exceptions.CartIsEmptyException;
import com.shop.shopapi.exceptions.UserNotFoundException;
import com.shop.shopapi.persistence.model.Cart;
import com.shop.shopapi.persistence.model.Coupon;
import com.shop.shopapi.persistence.model.Order;
import com.shop.shopapi.persistence.model.OrderDetails;
import com.shop.shopapi.persistence.model.PurchaseItem;
import com.shop.shopapi.persistence.model.User;
import com.shop.shopapi.persistence.model.dtos.OrderDto;
import com.shop.shopapi.persistence.repository.CartRepository;
import com.shop.shopapi.persistence.repository.OrderDetailsRepository;
import com.shop.shopapi.persistence.repository.OrderRepository;
import com.shop.shopapi.persistence.repository.PurchaseItemRepository;
import com.shop.shopapi.persistence.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    PurchaseItemRepository purchaseItemRepository;

    public Long setOrder(Long userId) throws UserNotFoundException, CartIsEmptyException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            throw new UserNotFoundException("User not found");
        User user = optionalUser.get();

        Cart cart = user.getCart();
        if (cart == null)
            throw new CartIsEmptyException("Cart is empty");

        OrderDetails orderDetails = new OrderDetails();
        orderDetailsRepository.save(orderDetails);

        Double totalPriceAfterCoupon = cart.getTotalPriceAfterCoupon();
        Double totalPrice = cart.getTotalPrice();
        Integer totalProductCount = cart.getTotalProductCount();
        Coupon coupon = cart.getCoupon();

        Boolean isCouponUsed = false;
        for (PurchaseItem purchaseItem : cart.getPurchaseItemList()) {
            if (purchaseItem.getProduct().getCoupon() != null) {
                isCouponUsed = true;
            }
            purchaseItem.setOrderDetails(orderDetails);
            purchaseItem.setStatus("Ordered");
        }

        Order order = new Order();

        order.setFinalPrice(cart.getTotalPriceAfterCoupon());
        order.setIsCouponUsed(isCouponUsed);
        order.setStatus("Ordered");
        order.setTotalItem(cart.getTotalProductCount());
        order.setUser(user);
        order.setCartOrdered(cart);

        cart.setTotalPrice(0.0);
        cart.setTotalPriceAfterCoupon(0.0);
        cart.setTotalProductCount(0);

        orderRepository.save(order);

        if (coupon != null) {
            orderDetails.setCoupon(coupon);
            orderDetails.setTotalPriceAfterCoupon(totalPriceAfterCoupon);
        }
        orderDetails.setOrder(order);
        orderDetails.setPurchaseItemList(null);
        orderDetails.setPurchaseItemList(purchaseItemRepository.findByOrderDetails(orderDetails));
        orderDetails.setTotalPrice(totalPrice);
        orderDetails.setTotalProductCount(totalProductCount);
        orderDetails.setStatus("Ordered");
        orderDetails.setUser(user);
        orderDetails.setId(orderDetails.getId());

        orderDetailsRepository.save(orderDetails);

        return order.getId();

    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders) {
            orderDtos.add(order.toOrderDto());
        }

        return orderDtos;
    }

}
