package com.dealerstat.controller;

import com.dealerstat.entity.User;
import com.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    @GetMapping("/")
    public String showHelloWorld() {
        return "<a href=\"/dealer-stat\"> go to main page</a>";
    }

    @GetMapping("/dealer-stat")
    public String showMainPage() {
        return "<a href=\"/dealer-stat/dealer\"> go to dealers</a>";
    }


}
