package com.dealerstat.controller;

import com.dealerstat.entity.User;
import com.dealerstat.service.AdminService;
import com.dealerstat.service.ThereIsNoSuchUserException;
import com.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    public AdminService adminService;

    @Autowired
    public User user;


}
