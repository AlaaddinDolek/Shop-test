package com.shop.shopapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.shopapi.exceptions.CartIsEmptyException;
import com.shop.shopapi.exceptions.CouponHasBeenUsedBeforeException;
import com.shop.shopapi.exceptions.CouponHasRunOutException;
import com.shop.shopapi.exceptions.CouponNotFoundException;
import com.shop.shopapi.exceptions.ProductAlreadyExistsInTheCartException;
import com.shop.shopapi.exceptions.ProductNotFoundException;
import com.shop.shopapi.exceptions.UserNotFoundException;
import com.shop.shopapi.persistence.model.Cart;
import com.shop.shopapi.persistence.model.Coupon;
import com.shop.shopapi.persistence.model.PurchaseItem;
import com.shop.shopapi.persistence.model.User;
import com.shop.shopapi.persistence.model.dtos.CartDto;
import com.shop.shopapi.persistence.repository.CartRepository;
import com.shop.shopapi.persistence.repository.CouponRepository;
import com.shop.shopapi.persistence.repository.ProductRepository;
import com.shop.shopapi.persistence.repository.PurchaseItemRepository;
import com.shop.shopapi.persistence.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    PurchaseItemRepository purchaseItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseItemService purchaseItemService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CouponRepository couponRepository;

    public Long addToCart(Long userId, Long productId, Integer productCount)
            throws UserNotFoundException, ProductNotFoundException, ProductAlreadyExistsInTheCartException {
        PurchaseItem purchaseItem = purchaseItemService.purchaseItem(userId, productId, productCount);

        User user = purchaseItem.getUser();

        Cart cart = user.getCart();

        for (PurchaseItem eachPurchaseItem : cart.getPurchaseItemList()) {
            if (eachPurchaseItem.getProduct().getId().equals(productId)) {
                throw new ProductAlreadyExistsInTheCartException("Product already exists in the cart");
            }
        }

        Integer count = cart.getTotalProductCount();
        Double totalPrice = cart.getTotalPrice();
        Double totalPriceAfterCoupon = cart.getTotalPriceAfterCoupon();

        purchaseItem.setCart(cart);

        count += purchaseItem.getProductCount();
        Double purchaseItemPrice = purchaseItem.getProduct().getPrice() * purchaseItem.getProductCount();
        totalPrice += purchaseItemPrice;

        if (purchaseItem.getProduct().getCoupon() != null) {
            totalPriceAfterCoupon += purchaseItemPrice * (100 - purchaseItem.getProduct().getCoupon().getDiscountRate())
                    / 100;
        } else {
            totalPriceAfterCoupon += purchaseItemPrice;
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalProductCount(count);
        cart.setTotalPriceAfterCoupon(totalPriceAfterCoupon);
        cart.setStatus("Not ordered");
        cart.setUser(user);

        purchaseItemRepository.save(purchaseItem);
        cartRepository.save(cart);
        userRepository.save(user);

        return cart.getId();

    }

    public List<CartDto> getCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDto> cartDtos = new ArrayList<>();
        for (Cart cart : carts) {
            cartDtos.add(cart.toCartDto());
        }
        return cartDtos;
    }

    public CartDto removeFromCart(Long userId, Long produtId) throws UserNotFoundException, ProductNotFoundException {
        Optional<User> optionalUser = userRepository.findUserById(userId);
        if (!optionalUser.isPresent())
            throw new UserNotFoundException("User not found");
        User user = optionalUser.get();
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        Boolean isInCart = false;

        for (PurchaseItem purchaseItem : user.getCart().getPurchaseItemList()) {
            if (purchaseItem.getProduct().getId().equals(produtId)) {
                purchaseItem.setStatus("Removed");
                purchaseItemRepository.save(purchaseItem);
                user.getCart()
                        .setTotalProductCount(user.getCart().getTotalProductCount() - purchaseItem.getProductCount());
                user.getCart().setTotalPrice(user.getCart().getTotalPrice()
                        - (purchaseItem.getProduct().getPrice() * purchaseItem.getProductCount()));
                if (purchaseItem.getProduct().getCoupon() != null) {
                    user.getCart().setTotalPriceAfterCoupon(user.getCart().getTotalPriceAfterCoupon()
                            - (purchaseItem.getProduct().getPriceAfterCoupon() * purchaseItem.getProductCount()));
                } else {
                    user.getCart().setTotalPriceAfterCoupon(user.getCart().getTotalPriceAfterCoupon()
                            - (purchaseItem.getProduct().getPrice() * purchaseItem.getProductCount()));
                }
                isInCart = true;

            } else {
                purchaseItems.add(purchaseItem);
            }
        }
        if (isInCart == false)
            throw new ProductNotFoundException("Product not found in the cart");
        user.getCart().setPurchaseItemList(purchaseItems);
        cartRepository.save(user.getCart());
        return user.getCart().toCartDto();
    }

    public CartDto useCoupon(Integer couponCode, Long userId) throws CouponNotFoundException, CartIsEmptyException,
            UserNotFoundException, CouponHasRunOutException, CouponHasBeenUsedBeforeException {
        Optional<Coupon> optionalCoupon = couponRepository.findCouponByCouponCode(couponCode);
        if (!optionalCoupon.isPresent())
            throw new CouponNotFoundException("Coupon not found");
        Coupon coupon = optionalCoupon.get();

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            throw new UserNotFoundException("User not found");
        User user = optionalUser.get();

        if (user.getCart().getCoupon() != null) {
            if (user.getCart().getCoupon().getId() == coupon.getId())
                throw new CouponHasBeenUsedBeforeException("Coupon has been used before");
        }
        if (user.getCart().getPurchaseItemList().isEmpty())
            throw new CartIsEmptyException("Cart is empty");

        user.getCart().setCoupon(coupon);
        user.getCart().setTotalPriceAfterCoupon(
                ((100 - coupon.getDiscountRate()) * user.getCart().getTotalPriceAfterCoupon()) / 100);
        if (coupon.getCouponCount() == 0)
            throw new CouponHasRunOutException("Coupon has run out");
        user.getCart().getCoupon().setCouponCount(user.getCart().getCoupon().getCouponCount() - 1);
        user.getCart().setStatus("Not ordered");
        user.getCart().setUser(user);

        return userRepository.save(user).getCart().toCartDto();

    }

}
