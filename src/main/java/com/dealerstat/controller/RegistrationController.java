package com.dealerstat.controller;

import com.dealerstat.entity.profile.User;
import com.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String createRegistration(@RequestBody User newUser) throws Exception {
        if (userService.registration(newUser)) {
            return "Registration!";
        } else {
            return "Ops...";
        }
    }
}
