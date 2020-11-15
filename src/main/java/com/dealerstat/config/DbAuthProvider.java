package com.dealerstat.config;

import com.dealerstat.entity.profile.Password;
import com.dealerstat.entity.profile.User;
import com.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DbAuthProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public DbAuthProvider(UserService userService) {
        this.userService = userService;
    }

    @lombok.SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findUserByEmail(email);

        if (Objects.isNull(user)) {
            throw new BadCredentialsException("Invalid email");
        }

        if (Password.check(Password.getSaltedHash(password), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        if (Objects.isNull(user.getApproved()) && Objects.equals(user.getRole(), "ROLE_DEALER")) {
            throw new BadCredentialsException("Mail not confirmed");
        }

        if (!user.getApproved() && Objects.equals(user.getRole(), "ROLE_DEALER")) {
            throw new BadCredentialsException("Profile not approved");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new UsernamePasswordAuthenticationToken(email, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Objects.equals(authentication, UsernamePasswordAuthenticationToken.class);
    }

}
