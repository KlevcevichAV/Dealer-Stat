package com.dealerstat.controller;

import com.dealerstat.entity.profile.Profile;
import com.dealerstat.entity.profile.User;
import com.dealerstat.redis.DealerToken;
import com.dealerstat.redis.VerificationToken;
import com.dealerstat.repository.DealerTokenRepository;
import com.dealerstat.service.ProfileService;
import com.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;
    private final DealerTokenRepository dealerTokenRepository;

    @Autowired
    public ProfileController(ProfileService profileService, UserService userService, DealerTokenRepository dealerTokenRepository) {
        this.profileService = profileService;
        this.userService = userService;
        this.dealerTokenRepository = dealerTokenRepository;
    }

    @GetMapping("/dealer")
    public List<Profile> showDealers(Model model) {
        List<Profile> dealers = profileService.findProfiles();
        model.addAttribute("dealers", dealers);
        return dealers;
    }

    @GetMapping("/editProfile")
    public void editProfile(@RequestBody Profile profile) {
        profileService.editProfile(profile);
//        return "redirect:/dealer/" + profile.getUser().getId();
    }

    @GetMapping("/dealer/{id}")
    public Profile showDealer(@PathVariable int id, Model model) {
        Profile profile = profileService.findProfileById(id);
        model.addAttribute("profile", profile);
        return profile;
    }

    @GetMapping("/dealer/top")
    public List<Profile> showTopDealers(Model model) {
        List<Profile> dealers = profileService.findTopProfiles();
        model.addAttribute("topDealers", dealers);
        return dealers;
    }

    @GetMapping("/dealer/topTags")
    public List<Profile> showTopDealersTags(@RequestBody Set<String> tags, Model model) {
        List<Profile> dealers = profileService.findTopProfilesTags(tags);
        model.addAttribute("topDealers", dealers);
        model.addAttribute("tag", tags);
        return dealers;
    }

    @GetMapping("/dealer/max")
    public List<Profile> getMaxDealers(@RequestBody int max, Model model) {
        List<Profile> dealers = profileService.findMaxDealers(max);
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
    public List<Profile> getMinAndMaxDealers(@RequestBody int ratings[], Model model) {
        List<Profile> dealers = profileService.getMinAndMaxDealers(ratings[0], ratings[1]);
        model.addAttribute("maxAndMinDealers", dealers);
        return dealers;
    }

    @GetMapping("/activate/{code}")
    public String activateProfile(@PathVariable String code) {
        DealerToken dealerToken = dealerTokenRepository.findById(code).get();
        if (Objects.isNull(dealerToken)) return "-";
        User user = userService.findUserByEmail(dealerToken.getEmail());
        if (Objects.isNull(user)) return "doesn't activate";
        VerificationToken newVerificationToken = new VerificationToken();
        VerificationToken oldVerificationToken = new VerificationToken(code);
        if (newVerificationToken.checkToken(oldVerificationToken)) {
            user.setApproved(false);
            userService.edit(user);
            dealerTokenRepository.delete(dealerToken);
            return "activate";
        }
        return "token is deprecated";
    }
}
