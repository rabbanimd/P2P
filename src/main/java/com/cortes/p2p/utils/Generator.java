package com.cortes.p2p.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class Generator {
    public static String generateUserAuthToken() {
        return UUID.randomUUID().toString();
    }

    public static String generateUserPasswordHash(String password) {
        return getEncoder().encode(password);
    }

    private static PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
