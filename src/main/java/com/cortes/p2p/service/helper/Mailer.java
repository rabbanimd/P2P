package com.cortes.p2p.service.helper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class Mailer {
    /**
     * When we will have more constants than we will create
     * another class
     * like P2PContant.java
     */
    private static final String SENDER_EMAIL = "waviangroup@gmail.com";
    private static final String SENDER_NAME = "P2P";
    private static final String SUBJECT = "Activate your account";
    private static final String VERIFICATION_URL = "http://localhost:8080//api/v1/auth" +
            "/authenticate/[token]";
    private static final String CONTENT = "Dear [name],\n" +
            "Please click the link below to activate your account\n" +
            "[url] \n" +
            "Thank you\n" +
            "Wavian Group";
    private final String AUTHSERVER_ADDRESS = "http:localhost:8080/api/v1/auth/authenticate/";
    @Autowired
    private final JavaMailSender mailSender;

    @Async
    public void sendVerificationEmail(String email, String name, String token)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(SENDER_EMAIL, SENDER_NAME);
        helper.setTo(email);
        helper.setSubject(SUBJECT);
        String content = CONTENT.replace("[name]", name);
        content = content.replace("[url]", VERIFICATION_URL);
        content = content.replace("[token]", token);
        helper.setText(content);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
}
