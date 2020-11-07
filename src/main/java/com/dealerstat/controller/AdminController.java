package com.dealerstat.controller;

import com.dealerstat.entity.User;
import com.dealerstat.service.AdminService;
import com.dealerstat.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    public AdminService adminService;

    @Autowired
    public User user;


}
