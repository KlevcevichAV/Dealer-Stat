package com.dealerstat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/123")
    public String showHelloWorld() {
        return "home";
    }

    @GetMapping("/dealer-stat")
    public String showMainPage() {
        return "main";
    }


}
