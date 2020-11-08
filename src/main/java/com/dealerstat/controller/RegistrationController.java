package com.dealerstat.controller;

import com.dealerstat.entity.profile.User;
import com.dealerstat.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserServiceImpl userService;

    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration() {
        return "create/registration";
    }

    @PostMapping
    public String createRegistration(@RequestBody User newUser) {
        userService.registration(newUser);
        return "redirect:/dealer";
    }
}
