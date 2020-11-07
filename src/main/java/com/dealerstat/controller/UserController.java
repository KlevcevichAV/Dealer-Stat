package com.dealerstat.controller;

import com.dealerstat.entity.Comment;
import com.dealerstat.entity.User;
import com.dealerstat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Qualifier("getUserService")
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
    public String registrationDealer(@RequestBody User user) throws Exception {
        return userService.registration(user);
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

    @PutMapping("/dealer-stat/dealer-approved/{id}")
    public void setDealerApproved(@PathVariable int id){
        if("ADMIN".equals(user.getRole())){
            adminService.setDealerApproved(id);
            return;
        }
        throw new ThereIsNoSuchUserException();
    }

    @PutMapping("/dealer-stat/dealer-unapproved/{id}")
    public void setDealerUnapproved(@PathVariable int id){
        if("ADMIN".equals(user.getRole())){
            adminService.setDealerUnapproved(id);
            return;
        }
        throw new ThereIsNoSuchUserException();
    }

    @PostMapping(value = "/dealer-stat/registration-dealer-guest", consumes = "application/json", produces = "application/json")
    public String registrationDealerGuest(@RequestBody User user){
        if("ANON".equals(user.getRole())){
            return userService.registrationDealerGuest(user);
        }
        throw new ThereIsNoSuchUserException();
    }

    @GetMapping("/dealer-stat/unapproved-comment")
    public List<Comment> getCommentsUnapproved(){
        if("ADMIN".equals(user.getRole())){
            return adminService.getCommentsForApproved();
        }
        throw new ThereIsNoSuchUserException();
    }

    @GetMapping("/dealer-stat/unapproved-comment/{id}")
    public Comment getUnapprovedComment(@PathVariable int id){
        if("ADMIN".equals(user.getRole())){
            return adminService.getComment(id);
        }
        throw new ThereIsNoSuchUserException();
    }

    @PutMapping("/dealer-stat/comment-approved/{id}")
    public void setCommentApproved(@PathVariable int id){
        if("ADMIN".equals(user.getRole())){
            adminService.setCommentApproved(id);
            return;
        }
        throw new ThereIsNoSuchUserException();
    }

    @PutMapping("/dealer-stat/comment-unapproved/{id}")
    public void setCommentUnapproved(@PathVariable int id){
        if("ADMIN".equals(user.getRole())){
            adminService.setCommentUnapproved(id);
            return;
        }
        throw new ThereIsNoSuchUserException();
    }
}
