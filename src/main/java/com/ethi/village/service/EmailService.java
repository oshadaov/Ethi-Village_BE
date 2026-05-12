package com.ethi.village.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.username}")
    private String authenticatedEmail;

    public void sendBookingEmail(String from, String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        try {
            helper.setFrom(authenticatedEmail, "Guest: " + from); 
        } catch (UnsupportedEncodingException e) {
            helper.setFrom(authenticatedEmail);
        }
        
        helper.setReplyTo(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        
        mailSender.send(message);
    }
}
