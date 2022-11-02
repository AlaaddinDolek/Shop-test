package com.shop.shopapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.shopapi.exceptions.UserNotFoundException;
import com.shop.shopapi.exceptions.UsernameAlreadyExistsException;
import com.shop.shopapi.persistence.model.Cart;
import com.shop.shopapi.persistence.model.User;
import com.shop.shopapi.persistence.model.dtos.UserDto;
import com.shop.shopapi.persistence.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long createUser(User user)
            throws UsernameAlreadyExistsException {
        Optional<User> userOptional = userRepository.findUserByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setPassword(user.getPassword());
        Cart cart = new Cart();
        newUser.setCart(cart);

        User savedUser = userRepository.save(newUser);
        return savedUser.getId();
    }

    public UserDto updateUsername(Long id, String newUsername)
            throws UsernameAlreadyExistsException, UserNotFoundException {
        Optional<User> userById = userRepository.findUserById(id);
        if (!userById.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }
        Optional<User> user = userRepository.findUserByUsername(newUsername);
        if (user.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }
        userById.get().setUsername(newUsername);
        userRepository.save(userById.get());
        return userById.get().toUserDto();
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findUserById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("User not found.");
        userRepository.deleteById(id);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(user.toUserDto());
        }
        return userDtos;
    }

    public UserDto findUserByUsername(String username) throws UserNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (!user.isPresent())
            throw new UserNotFoundException("User not found.");

        return user.get().toUserDto();
    }

    public UserDto findUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findUserById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("User not found.");

        return user.get().toUserDto();
    }

}
