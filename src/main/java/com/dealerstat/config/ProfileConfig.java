package com.dealerstat.config;

import com.dealerstat.service.ProfileService;
import com.dealerstat.service.ProfileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileConfig {

    @Bean
    public ProfileService getProfileService(){
        return new ProfileServiceImpl();
    }
}
