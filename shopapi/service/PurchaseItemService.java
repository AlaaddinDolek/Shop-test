package com.shop.shopapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.shopapi.exceptions.ProductNotFoundException;
import com.shop.shopapi.exceptions.UserNotFoundException;
import com.shop.shopapi.persistence.model.Product;
import com.shop.shopapi.persistence.model.PurchaseItem;
import com.shop.shopapi.persistence.model.User;
import com.shop.shopapi.persistence.repository.ProductRepository;
import com.shop.shopapi.persistence.repository.PurchaseItemRepository;
import com.shop.shopapi.persistence.repository.UserRepository;

@Service
public class PurchaseItemService {

    @Autowired
    PurchaseItemRepository purchaseItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public PurchaseItem purchaseItem(Long userId, Long productId, Integer productCount)
            throws UserNotFoundException, ProductNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            throw new UserNotFoundException("User not found");
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotFoundException("Product not found");

        PurchaseItem purchaseItem = new PurchaseItem();
        User user = optionalUser.get();
        Product product = optionalProduct.get();

        purchaseItem.setProduct(product);
        purchaseItem.setStatus("inCart");
        purchaseItem.setUser(user);
        purchaseItem.setProductCount(productCount);

        purchaseItemRepository.save(purchaseItem);

        return purchaseItem;

    }
}