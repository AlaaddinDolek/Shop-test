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

import com.shop.shopapi.persistence.model.dtos.CartDto;
import com.shop.shopapi.persistence.model.dtos.PurchaseItemDto;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cart")
    @Where(clause = "status='inCart'")
    private List<PurchaseItem> purchaseItemList = new ArrayList<>();

    private Double totalPrice = 0.0;
    private Double totalPriceAfterCoupon = 0.0;

    private Integer totalProductCount = 0;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Coupon coupon;

    public CartDto toCartDto() {
        CartDto cartDto = new CartDto();
        List<PurchaseItemDto> items = new ArrayList<>();
        for (PurchaseItem purchaseItem : purchaseItemList) {
            items.add(purchaseItem.toPurchaseItemDto());
        }
        cartDto.setItemsInCart(items);
        cartDto.setTotalPrice(totalPrice);
        cartDto.setTotalProductCount(totalProductCount);
        cartDto.setPriceAfterCoupon(totalPriceAfterCoupon);
        cartDto.setProfitDueToDiscount(totalPrice - totalPriceAfterCoupon);

        return cartDto;
    }

    public Cart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Double calculateTotalPrice() {
        Double total = 0.0;
        for (PurchaseItem purchaseItem : purchaseItemList) {
            total += purchaseItem.getProduct().getPrice() * purchaseItem.getProductCount();
        }
        return total;
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

    public List<PurchaseItem> getPurchaseItemList() {
        return purchaseItemList;
    }

    public void setPurchaseItemList(List<PurchaseItem> purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

}
