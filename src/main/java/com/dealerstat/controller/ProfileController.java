package com.dealerstat.controller;

import com.dealerstat.entity.Profile;
import com.dealerstat.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @Autowired
    public ProfileService profileService;

    @GetMapping("/dealer-stat/dealer/{id}")
    public Profile showDealer(@PathVariable int id) {
        return profileService.getProfile(id);
    }
}
