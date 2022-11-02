package com.shop.shopapi.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.shop.shopapi.persistence.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(Long id);

    void deleteById(Long id);
}
