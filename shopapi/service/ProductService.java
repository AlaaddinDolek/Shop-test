package com.shop.shopapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.shopapi.exceptions.CouponHasBeenUsedBeforeException;
import com.shop.shopapi.exceptions.CouponNotFoundException;
import com.shop.shopapi.exceptions.ProductNotFoundException;
import com.shop.shopapi.exceptions.SellerNotFoundException;
import com.shop.shopapi.persistence.model.Coupon;
import com.shop.shopapi.persistence.model.Product;
import com.shop.shopapi.persistence.model.dtos.ProductDto;
import com.shop.shopapi.persistence.repository.CouponRepository;
import com.shop.shopapi.persistence.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    public Long insertProduct(ProductDto productDto) {
        return productRepository.save(productDto.toProductEntity()).getId();
    }

    public ProductDto getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            throw new ProductNotFoundException("Product not found");
        return optionalProduct.get().toProductDto();
    }

    public ProductDto updateProduct(Product product) throws ProductNotFoundException {

        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (!optionalProduct.isPresent())
            throw new ProductNotFoundException("Product not found");

        Product savedProduct = optionalProduct.get();
        savedProduct.setDiscription(product.getDiscription());
        savedProduct.setName(product.getName());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setType(product.getType());

        if (product.getCoupon() != null) {
            savedProduct.setCoupon(product.getCoupon());

            Double priceAfterCoupon = product.getCoupon().getDiscountRate() * product.getPrice();
            savedProduct.setPriceAfterCoupon(priceAfterCoupon);
        }
        return productRepository.save(savedProduct).toProductDto();
    }

    public void deleteById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent())
            throw new ProductNotFoundException("Product not found");

        productRepository.deleteById(id);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(product.toProductDto());
        }
        return productDtos;
    }

    public List<ProductDto> getProductPriceGreaterThan(Double productPrice) throws ProductNotFoundException {
        List<Product> products = productRepository.findByPriceGreaterThan(productPrice);
        if (products.isEmpty())
            throw new ProductNotFoundException("There is no product with price greater than:" + productPrice);

        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(product.toProductDto());
        }
        return productDtos;
    }

    public List<ProductDto> getProductsByType(String type) throws ProductNotFoundException {
        List<Product> products = productRepository.findByType(type);
        if (products.isEmpty())
            throw new ProductNotFoundException("There is no product with the tpye:" + type);

        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(product.toProductDto());
        }
        return productDtos;
    }

    public List<ProductDto> getProductBySeller(String seller) throws SellerNotFoundException {
        List<Product> products = productRepository.findBySeller(seller);
        if (products.isEmpty())
            throw new SellerNotFoundException("Seller not found");

        List<ProductDto> pRoductDtos = new ArrayList<>();
        for (Product product : products) {
            pRoductDtos.add(product.toProductDto());
        }

        return pRoductDtos;
    }

    public ProductDto setCoupon(Long productId, Integer couponCode)
            throws ProductNotFoundException, CouponNotFoundException, CouponHasBeenUsedBeforeException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotFoundException("Product not found");
        Optional<Coupon> optionalCoupon = couponRepository.findCouponByCouponCode(couponCode);
        if (!optionalCoupon.isPresent())
            throw new CouponNotFoundException("Coupon not found");

        Product product = optionalProduct.get();
        Coupon coupon = optionalCoupon.get();

        product.setCoupon(coupon);
        product.setPriceAfterCoupon(product.getPrice() * (100 - coupon.getDiscountRate()) / 100);

        productRepository.save(product);
        couponRepository.save(coupon);

        return product.toProductDto();
    }

}