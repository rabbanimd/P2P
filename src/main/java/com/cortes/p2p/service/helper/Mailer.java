package com.cortes.p2p.service.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class Mailer {
    private final String COMPANY_NAME = "P2P";
    private final String AUTHSERVER_ADDRESS = "http:localhost:8080/api/v1/auth/authenticate/";
    /**
     * When we will have more constants than we will create
     * another class
     * like P2PContant.java
     */
    @Value("${spring.mail.username}")
    private String ADMIN_MAIL;
    private JavaMailSender mailSender;

    @Async
    public void sendVerificationEmail(String email, String name, String token)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = email;
        String fromAddress = ADMIN_MAIL;
        String senderName = COMPANY_NAME;
        String subject = "Please verify your registration";
        String url = AUTHSERVER_ADDRESS + token;
        String content = "Dear " + name + ",<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"" + url + "\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "P2P.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }
}
