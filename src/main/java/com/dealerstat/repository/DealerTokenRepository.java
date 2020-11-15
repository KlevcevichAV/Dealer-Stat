package com.dealerstat.repository;

import com.dealerstat.redis.DealerToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerTokenRepository extends CrudRepository<DealerToken, String> {
    DealerToken findByEmail(String email);

    DealerToken findByToken(String token);
}
