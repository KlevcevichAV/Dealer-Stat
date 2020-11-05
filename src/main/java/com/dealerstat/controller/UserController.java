package com.dealerstat.controller;

import com.dealerstat.entity.User;
import com.dealerstat.service.AdminService;
import com.dealerstat.service.ThereIsNoSuchUserException;
import com.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public AdminService adminService;

    @Autowired
    public User user;

    @GetMapping("/dealer-stat/dealer")
    public List<User> showDealers() {
        return userService.getUsers();
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
        if (userService.edit(user, this.user)) {
            this.user = user;
            return "Edited";
        }
        return "Don't edited";
    }

    @GetMapping(value = "/dealer-stat/logIn", consumes = "application/json", produces = "application/json")
    public String logIn(@RequestBody User user) {
        this.user = userService.logIn(user);
        if (this.user == null) return "No log in";
        if (this.user.getId() == 0) return "No log in";
        return "Log in";
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

    @GetMapping("/dealer-stat/unapproved-dealer")
    public List<User> getDealersUnapproved(){
        if("ADMIN".equals(user.getRole())){
            return adminService.getUsersForApproved();
        }
        throw new ThereIsNoSuchUserException();
    }

    @GetMapping("/dealer-stat/unapproved-dealer/{id}")
    public User getUnapprovedUser(@PathVariable int id){
        if("ADMIN".equals(user.getRole())){
            return adminService.getUser(id);
        }
        throw new ThereIsNoSuchUserException();
    }

    @PostMapping("/dealer-stat/dealer-approved/{id}")
    public void setDealerApproved(@PathVariable int id){
        if("ADMIN".equals(user.getRole())){
            adminService.setDealerApproved(id);
            return;
        }
        throw new ThereIsNoSuchUserException();
    }

    @PostMapping("/dealer-stat/dealer-unapproved/{id}")
    public void setDealerUnapproved(@PathVariable int id){
        if("ADMIN".equals(user.getRole())){
            adminService.setDealerUnapproved(id);
            return;
        }
        throw new ThereIsNoSuchUserException();
    }
}
