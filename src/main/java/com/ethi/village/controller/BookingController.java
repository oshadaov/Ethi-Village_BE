package com.ethi.village.controller;

import com.ethi.village.dto.BookingInquiryDTO;
import com.ethi.village.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/booking")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private EmailService emailService;

    @Value("${app.contact.email}")
    private String adminEmail;

    @PostMapping("/inquiry")
    public ResponseEntity<String> sendInquiry(@RequestBody BookingInquiryDTO inquiry) {
        String subject = "New Booking Inquiry from " + inquiry.getFullName();
        StringBuilder body = new StringBuilder();
        body.append("New Booking Inquiry Details:\n\n");
        body.append("Full Name: ").append(inquiry.getFullName()).append("\n");
        body.append("Email: ").append(inquiry.getEmail()).append("\n");
        body.append("Phone: ").append(inquiry.getPhone()).append("\n");
        body.append("Nationality: ").append(inquiry.getNationality()).append("\n");
        body.append("Preferred Date: ").append(inquiry.getPreferredDate()).append("\n");
        body.append("Guests: ").append(inquiry.getGuests()).append("\n");
        body.append("Experience: ").append(inquiry.getExperience()).append("\n");
        body.append("Accommodation: ").append(inquiry.getAccommodation()).append("\n");
        body.append("Pickup: ").append(inquiry.getPickup()).append("\n");
        body.append("Message: ").append(inquiry.getMessage()).append("\n");

        try {
            emailService.sendBookingEmail(inquiry.getEmail(), adminEmail, subject, body.toString());
            return ResponseEntity.ok("Booking inquiry sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send booking inquiry: " + e.getMessage());
        }
    }
}
