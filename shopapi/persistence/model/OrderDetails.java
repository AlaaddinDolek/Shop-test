package com.shop.shopapi.persistence.model;

import java.util.ArrayList;
import java.util.List;

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

import com.shop.shopapi.persistence.model.dtos.OrderDetailsDto;
import com.shop.shopapi.persistence.model.dtos.PurchaseItemDto;

@Entity
@Table(name = "orderDetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Order order;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "orderDetails")
    @Where(clause = "status='Ordered'")
    private List<PurchaseItem> purchaseItemList = new ArrayList<>();

    private Double totalPrice = 0.0;
    private Double totalPriceAfterCoupon = 0.0;

    private Integer totalProductCount = 0;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Coupon coupon;

    public OrderDetailsDto tOrderDetailsDto() {
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();

        List<PurchaseItemDto> purchaseItemDtoList = new ArrayList<>();

        Boolean isCouponUsed = false;
        for (PurchaseItem purchaseItem : purchaseItemList) {
            if (purchaseItem.getProduct().getCoupon() != null) {
                isCouponUsed = true;
            }
            purchaseItemDtoList.add(purchaseItem.toPurchaseItemDto());
        }

        if (coupon != null) {
            orderDetailsDto.setCouponName(coupon.getCouponName());
            orderDetailsDto.setIsCouponUsed(true);
            orderDetailsDto.setProfitDueToDiscount(totalPrice - totalPriceAfterCoupon);
            orderDetailsDto.setTotalPriceAfterCoupon(totalPriceAfterCoupon);
        } else if (isCouponUsed == true) {
            orderDetailsDto.setIsCouponUsed(isCouponUsed);
        }
        orderDetailsDto.setTotalPrice(totalPrice);
        orderDetailsDto.setUserName(user.getUsername());
        orderDetailsDto.setStatus("Ordered");
        orderDetailsDto.setTotalProductCount(totalProductCount);
        orderDetailsDto.setPurchaseItemList(purchaseItemDtoList);
        orderDetailsDto.setUserName(user.getName());

        return orderDetailsDto;
    }

    public OrderDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<PurchaseItem> getPurchaseItemList() {
        return purchaseItemList;
    }

    public void setPurchaseItemList(List<PurchaseItem> purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPriceAfterCoupon() {
        return totalPriceAfterCoupon;
    }

    public void setTotalPriceAfterCoupon(Double totalPriceAfterCoupon) {
        this.totalPriceAfterCoupon = totalPriceAfterCoupon;
    }

    public Integer getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(Integer totalProductCount) {
        this.totalProductCount = totalProductCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
