package com.dealerstat.controller;

import com.dealerstat.entity.profile.Profile;
import com.dealerstat.entity.profile.User;
import com.dealerstat.response.UserResponse;
import com.dealerstat.service.ProfileServiceImpl;
import com.dealerstat.service.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ProfileController {

    public final ProfileServiceImpl profileService;
    public final UserServiceImpl userService;

    public ProfileController(ProfileServiceImpl profileService, UserServiceImpl userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping("/dealer")
    public String showDealers(Model model) {
        List<Profile> dealers = profileService.getProfiles();
        model.addAttribute("dealers", dealers);
        return "lists/dealers";
    }

    @GetMapping("/editProfile")
    public String editProfile(@RequestBody Profile profile) {
        profileService.editProfile(profile);
        return "redirect:/dealer/" + profile.getUser().getId();
    }

    @GetMapping("/dealer/{id}")
    public String showDealer(@PathVariable int id, Model model) {
        Profile profile = profileService.getProfile(id);
        model.addAttribute("profile", profile);
        return "entity/profile";
    }

    @GetMapping("/dealer/top")
    public String showTopDealers(Model model) {
        List<Profile> dealers = profileService.getTopProfiles();
        model.addAttribute("topDealers", dealers);
        return "lists/topDealers";
    }

    @GetMapping("/dealer/topTags")
    public List<Profile> showTopDealersTags(@RequestBody Set<String> tags, Model model) {
        List<Profile> dealers = profileService.getTopProfilesTags(tags);
        model.addAttribute("topDealers", dealers);
        model.addAttribute("tag", tags);
        return dealers;
    }

    @GetMapping("/dealer/max")
    public List<Profile> getMaxDealers(@RequestBody int max, Model model) {
        List<Profile> dealers = profileService.getMaxDealers(max);
        model.addAttribute("maxDealers", dealers);
        return dealers;
    }

    @GetMapping("/dealer/min")
    public List<Profile> getMinDealers(@RequestBody int min, Model model) {
        List<Profile> dealers = profileService.getMinDealers(min);
        model.addAttribute("minDealers", dealers);
        return dealers;
    }

    @GetMapping("/dealer/minAndMax")
    public List<Profile> getMinDealers(@RequestBody int ratings[], Model model) {
        List<Profile> dealers = profileService.getMinAndMaxDealers(ratings[0], ratings[1]);
        model.addAttribute("maxAndMinDealers", dealers);
        return dealers;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> readAll() {
        List<User> users = userService.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : users){
            userResponses.add(new UserResponse(user.getId(), user.getEmail(), user.getFirstName()));
        }
        return userResponses;
    }
}
