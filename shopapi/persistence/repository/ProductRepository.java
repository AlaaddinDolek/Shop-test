package com.shop.shopapi.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.shop.shopapi.persistence.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    void deleteById(Long id);

    Optional<Product> findById(Long id);

    List<Product> findByPriceGreaterThan(Double price);

    List<Product> findByType(String type);

    List<Product> findBySeller(String seller);

}
