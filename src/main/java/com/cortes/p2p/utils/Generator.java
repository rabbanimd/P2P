package com.cortes.p2p.utils;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.UUID;

@Component
public class Generator {
    private String generatedSalt;

    public Generator() {
        this.generatedSalt = BCrypt.gensalt(8);;
    }

    public String generateUserAuthToken() {
        return UUID.randomUUID().toString();
    }

    public String generateUserPasswordHash(String password) {
        return BCrypt.hashpw(password, generatedSalt);
//        return getEncoder().encode(password);
    }

    public boolean verifyPassword(String password, String storePasswordHash) {
        return BCrypt.checkpw(password, storePasswordHash);
    }
}
