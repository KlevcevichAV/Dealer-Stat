package com.dealerstat.service;

import com.dealerstat.email.EmailService;
import com.dealerstat.entity.profile.Password;
import com.dealerstat.entity.profile.User;
import com.dealerstat.redis.DealerToken;
import com.dealerstat.repository.DealerTokenRepository;
import com.dealerstat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final String PREFIX = "ROLE_";
    private final UserRepository userRepository;
    private final DealerTokenRepository dealerTokenRepository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, DealerTokenRepository dealerTokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.dealerTokenRepository = dealerTokenRepository;
        this.emailService = emailService;
    }

    public List<User> getUsers() {
        return userRepository.findAllByApprovedAndRole(true, "ROLE_DEALER");
    }

    public User findDealer(int id) {
        return userRepository.findByIdAndRoleAndApproved(id, "ROLE_DEALER", true);
    }

    public void edit(User user) {
        User userFromDB = userRepository.findByEmail(user.getEmail());
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        if (Objects.nonNull(userFromDB)) {
            userRepository.save(user);
        }
    }

    public boolean registration(User user) throws Exception {
        User userFromDB = userRepository.findByEmail(user.getEmail());

        if (Objects.nonNull(userFromDB)) {
            return false;
        }

        if (Objects.isNull(user.getFirstName()) || Objects.isNull(user.getLastName())) {
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        user.setCreatedAt(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + '-' + calendar.get(Calendar.DAY_OF_MONTH));
        user.setRole(PREFIX + "DEALER");
        user.setPassword(Password.getSaltedHash(user.getPassword()));
        DealerToken dealerToken = new DealerToken(user.getEmail());
        String message = String.format("Hello, %s! \n" +
                "Welcome to Dealer Stat. Please, visit next link: http://localhost:8080/activate/%s", user.getFirstName(), dealerToken.getToken());
        emailService.sendSimpleMessage(user.getEmail(), "Activation code", message);

        dealerTokenRepository.save(dealerToken);
        userRepository.save(user);
        return true;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
