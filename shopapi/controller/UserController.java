package com.shop.shopapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.shopapi.exceptions.UserNotFoundException;
import com.shop.shopapi.exceptions.UsernameAlreadyExistsException;
import com.shop.shopapi.persistence.model.User;
import com.shop.shopapi.persistence.model.dtos.UserDto;
import com.shop.shopapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    @PostMapping
    public Long createUser(@RequestBody User user)
            throws UsernameAlreadyExistsException {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserDto updateUsername(@PathVariable("id") Long id, @RequestParam String newUsername)
            throws UsernameAlreadyExistsException, UserNotFoundException {
        return userService.updateUsername(id, newUsername);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long id) throws UserNotFoundException {
        userService.deleteUserById(id);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/username")
    public UserDto findUserByUsername(@RequestParam String username) throws UserNotFoundException {
        return userService.findUserByUsername(username);
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return userService.findUserById(id);
    }

}
