package com.dealerstat.redis;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Token")
public class DealerToken implements Serializable {
    private String id;
    private String email;
    private String token;

    public DealerToken() {
    }

    public DealerToken(String email) {
        this.email = email;
        this.token = new VerificationToken().getToken();
        this.id = token;
    }

    public DealerToken(String email, String token) {
        this.email = email;
        this.token = token;
        this.id = token;
    }

    public DealerToken(String id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}