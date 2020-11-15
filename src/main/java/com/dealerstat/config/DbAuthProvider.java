package com.dealerstat.config;

import com.dealerstat.entity.profile.User;
import com.dealerstat.service.UserServiceImpl;
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

    private final UserServiceImpl userService;

    @Autowired
    public DbAuthProvider(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findUserByEmail(email);

        if (user == null) {
            throw new BadCredentialsException("Invalid email");
        }

        if (!Objects.equals(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
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
