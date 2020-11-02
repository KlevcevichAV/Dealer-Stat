package com.dealerstat.controller;

import com.dealerstat.entity.User;
import com.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/dealer-stat/dealer")
    public List<User> showDealers() {
        return userService.getUsers();
    }

    @GetMapping("/dealer-stat/dealer/{id}")
    public User showDealer(@PathVariable int id) {
        System.out.println(userService.getUser(id));
        return userService.getUser(id);
    }
}
