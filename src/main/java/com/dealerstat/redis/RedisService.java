package com.dealerstat.redis;

import com.dealerstat.repository.DealerTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final DealerTokenRepository dealerTokenRepository;

    @Autowired
    public RedisService(DealerTokenRepository dealerTokenRepository) {
        this.dealerTokenRepository = dealerTokenRepository;
    }

    public void addToken(DealerToken dealerToken) {
        dealerTokenRepository.save(dealerToken);
    }

    public DealerToken findTokenByEmail(String email) {
        return dealerTokenRepository.findByEmail(email);
    }

    public void updateToken(DealerToken dealerToken) {
        dealerToken = new DealerToken(dealerToken.getEmail());
        dealerTokenRepository.save(dealerToken);
    }
}
