package com.dealerstat.recovery;

import com.dealerstat.email.EmailService;
import com.dealerstat.entity.profile.Password;
import com.dealerstat.entity.profile.User;
import com.dealerstat.redis.DealerToken;
import com.dealerstat.redis.VerificationToken;
import com.dealerstat.repository.DealerTokenRepository;
import com.dealerstat.repository.UserRepository;
import com.dealerstat.response.RecoveryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class RecoveryController {
    private final UserRepository userRepository;
    private final DealerTokenRepository dealerTokenRepository;
    private final EmailService emailService;

    @Autowired
    public RecoveryController(UserRepository userRepository, DealerTokenRepository dealerTokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.dealerTokenRepository = dealerTokenRepository;
        this.emailService = emailService;
    }


    @GetMapping("/auth/forgot_password")
    public String sendMessage(@RequestBody String mail) {
        User user = userRepository.findByEmail(mail);

        if (Objects.isNull(user)) {
            return "wrong email";
        }

        DealerToken dealerToken = new DealerToken(user.getEmail());
        String message = String.format("Hello, %s! \n" +
                "Password recovery code: %s", user.getFirstName(), dealerToken.getToken());
        emailService.sendSimpleMessage(user.getEmail(), "Activation code", message);

        dealerTokenRepository.save(dealerToken);
        return "Done";
    }

    @GetMapping("/auth/reset")
    public String reset(@RequestBody RecoveryResponse recoveryResponse) throws Exception {
        DealerToken dealerToken = dealerTokenRepository.findById(recoveryResponse.getCode()).get();
        if (Objects.isNull(dealerToken)) return "error";

        VerificationToken newVerificationToken = new VerificationToken();
        VerificationToken oldVerificationToken = new VerificationToken(dealerToken.getToken());
        if (newVerificationToken.checkToken(oldVerificationToken)) {
            User user = userRepository.findByEmail(dealerToken.getEmail());
            user.setPassword(Password.getSaltedHash(recoveryResponse.getPassword()));
            userRepository.save(user);
            dealerTokenRepository.delete(dealerToken);
            return "New password";
        }
        return "Old password";
    }

    @GetMapping("/auth/check_code")
    public String checkCode(@RequestBody String code) {
        DealerToken dealerToken = dealerTokenRepository.findById(code).get();
        VerificationToken newVerificationToken = new VerificationToken();
        VerificationToken oldVerificationToken = new VerificationToken(dealerToken.getToken());
        if (newVerificationToken.checkToken(oldVerificationToken)) {
            return code;
        } else return "Non";
    }
}
