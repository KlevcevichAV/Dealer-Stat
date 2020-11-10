package com.dealerstat.response;

public class UserResponse {
    private final int id;
    private final String email;
    private final String firstName;

    public UserResponse(int id, String email, String firstName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

}