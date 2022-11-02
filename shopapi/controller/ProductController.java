package com.shop.shopapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.shopapi.exceptions.CouponHasBeenUsedBeforeException;
import com.shop.shopapi.exceptions.CouponNotFoundException;
import com.shop.shopapi.exceptions.ProductNotFoundException;
import com.shop.shopapi.exceptions.SellerNotFoundException;
import com.shop.shopapi.persistence.model.Product;
import com.shop.shopapi.persistence.model.dtos.ProductDto;
import com.shop.shopapi.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired(required = false)
    private ProductService productService;

    @PostMapping
    public Long insertProduct(@RequestBody ProductDto productDto) {
        return productService.insertProduct(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(product);
    }

    @DeleteMapping
    public void deleteProductById(@RequestParam Long id) throws ProductNotFoundException {
        productService.deleteById(id);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/type")
    public List<ProductDto> getProductsByType(@RequestParam String type) throws ProductNotFoundException {
        return productService.getProductsByType(type);
    }

    @GetMapping("/greater-than")
    public List<ProductDto> getProductPriceGreaterThan(@RequestParam Double price) throws ProductNotFoundException {
        return productService.getProductPriceGreaterThan(price);
    }

    @GetMapping("/id")
    public ProductDto getProductById(@RequestParam Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/seller")
    public List<ProductDto> getProductBySeller(@RequestParam String seller) throws SellerNotFoundException {
        return productService.getProductBySeller(seller);
    }

    @PutMapping("/set_coupon")
    public ProductDto setCoupon(@RequestParam Long productId, Integer couponCode)
            throws ProductNotFoundException, CouponNotFoundException, CouponHasBeenUsedBeforeException {
        return productService.setCoupon(productId, couponCode);
    }
}
