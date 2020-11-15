package com.dealerstat.redis;

import java.util.Calendar;
import java.util.UUID;

public class VerificationToken {
    private static final int MINUTE = 60;
    private static final int HOUR = 3600;
    private static final int DAY = 86400;
    private static final int MONTH = 86400;
    private static final int YEAR = 86400;

    private String token;

    private int sum;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSum() {
        return sum;
    }

    public VerificationToken() {
        Calendar cal = Calendar.getInstance();
        sum = cal.get(Calendar.YEAR) * YEAR + cal.get(Calendar.MONTH) * MONTH + cal.get(Calendar.DAY_OF_MONTH) * DAY + cal.get(Calendar.HOUR) * HOUR + cal.get(Calendar.MINUTE) * MINUTE + cal.get(Calendar.SECOND);
        token = sum + "-" + UUID.randomUUID().toString();
    }

    public VerificationToken(String token) {
        sum = Integer.parseInt(token.substring(0, token.indexOf('-')));
        this.token = token;
    }

    public boolean checkToken(VerificationToken anotherToken) {
        return (this.sum - anotherToken.sum) <= DAY;
    }
}