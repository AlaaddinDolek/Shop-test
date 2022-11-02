package com.shop.shopapi.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.shop.shopapi.persistence.model.dtos.PurchaseItemDto;

@Entity
@Table(name = "purchaseItem")
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
    private OrderDetails orderDetails;

    private Integer productCount;

    public PurchaseItemDto toPurchaseItemDto() {
        PurchaseItemDto purchaseItemDto = new PurchaseItemDto();

        purchaseItemDto.setPrice(product.getPrice());
        purchaseItemDto.setProductName(product.getName());
        purchaseItemDto.setProductType(product.getType());
        purchaseItemDto.setSeller(product.getSeller());
        purchaseItemDto.setProductCount(productCount);
        if (product.getCoupon() != null) {
            purchaseItemDto.setCouponName(product.getCoupon().getCouponName());
            purchaseItemDto.setPriceAfterCoupon(product.getPriceAfterCoupon());
        }
        return purchaseItemDto;
    }

    public PurchaseItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

}
