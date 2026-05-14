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

    public void sendBookingEmail(String guestEmail, String to, String subject, String body) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        // IMPORTANT: We use the authenticated email as the 'From' to satisfy SMTP servers,
        // but we set the Display Name to the guest's email so the admin sees who it's from.
        helper.setFrom(authenticatedEmail, "Guest: " + guestEmail);
        
        // This is the most important part: 'Reply-To' allows the admin to reply directly to the guest.
        helper.setReplyTo(guestEmail);
        
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true); // true for HTML
        
        mailSender.send(message);
    }
}
