package com.dealerstat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
