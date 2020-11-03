package com.dealerstat.controller;

import com.dealerstat.entity.User;
import com.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public User user;

    @GetMapping("/dealer-stat/dealer")
    public List<User> showDealers() {
        return userService.getUsers();
    }

    @GetMapping("/dealer-stat/dealer/{id}")
    public User showDealer(@PathVariable int id) {
        return userService.getUser(id);
    }

    //!!!!!!!!
    @GetMapping("/dealer-stat/registration")
    public String createDealer() {
        return "hah";
    }

    @GetMapping("/dealer-stat/edit")
    public String editDealer() {
        return "hah";
    }

    @PutMapping(value = "/dealer-stat/edit", consumes = "application/json", produces = "application/json")
    public String editDealer(@RequestBody User user) {
        if(userService.edit(user, this.user)){
            this.user = user;
            return "Edited";
        }
        return "Don't edited";
    }

    @GetMapping(value = "/dealer-stat/logIn", consumes = "application/json", produces = "application/json")
    public String logIn(@RequestBody User user) {
        this.user = userService.logIn(user);
        if (this.user.getId() == 0) return "No log in";
        else return "Log in";
    }

    @GetMapping("/dealer-stat/logOut")
    public String logOut() {
        user = new User();
        return "redirect:/dealer-stat/dealer";
    }

    @PostMapping(value = "/dealer-stat/registration", consumes = "application/json", produces = "application/json")
    public String registrationDealer(@RequestBody User user) {
        userService.registration(user);
        return "redirect:/dealer-stat/dealer";
    }
}
