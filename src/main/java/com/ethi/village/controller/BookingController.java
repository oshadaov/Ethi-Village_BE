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
        String subject = "New Booking Inquiry: " + (inquiry.getExperience() != null && !inquiry.getExperience().isEmpty() ? inquiry.getExperience() : inquiry.getRoom());
        
        String htmlBody = "<html><body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>" +
                "<div style='max-width: 600px; margin: 0 auto; border: 1px solid #e1e1e1; border-radius: 10px; overflow: hidden;'>" +
                "<div style='background-color: #1e4a6d; color: white; padding: 20px; text-align: center;'>" +
                "<h1 style='margin: 0; font-size: 24px;'>New Booking Inquiry</h1>" +
                "</div>" +
                "<div style='padding: 20px;'>" +
                "<p>Hello Admin,</p>" +
                "<p>You have received a new booking inquiry from <strong>" + inquiry.getFullName() + "</strong>. You can reply directly to this email to contact the guest.</p>" +
                
                "<h3 style='color: #1e4a6d; border-bottom: 1px solid #eee; padding-bottom: 5px;'>Guest Information</h3>" +
                "<table style='width: 100%; border-collapse: collapse;'>" +
                "<tr><td style='padding: 8px 0; font-weight: bold; width: 150px;'>Full Name:</td><td>" + inquiry.getFullName() + "</td></tr>" +
                "<tr><td style='padding: 8px 0; font-weight: bold;'>Email:</td><td>" + inquiry.getEmail() + "</td></tr>" +
                "<tr><td style='padding: 8px 0; font-weight: bold;'>Phone/WhatsApp:</td><td>" + inquiry.getPhone() + "</td></tr>" +
                "<tr><td style='padding: 8px 0; font-weight: bold;'>Nationality:</td><td>" + (inquiry.getNationality() != null ? inquiry.getNationality() : "Not specified") + "</td></tr>" +
                "</table>" +
                
                "<h3 style='color: #1e4a6d; border-bottom: 1px solid #eee; padding-bottom: 5px; margin-top: 20px;'>Booking Details</h3>" +
                "<table style='width: 100%; border-collapse: collapse;'>" +
                "<tr><td style='padding: 8px 0; font-weight: bold; width: 150px;'>Preferred Date:</td><td>" + inquiry.getPreferredDate() + "</td></tr>" +
                "<tr><td style='padding: 8px 0; font-weight: bold;'>Guests:</td><td>" + inquiry.getGuests() + "</td></tr>" +
                "<tr><td style='padding: 8px 0; font-weight: bold;'>Experience:</td><td>" + (inquiry.getExperience() != null && !inquiry.getExperience().isEmpty() ? inquiry.getExperience() : "Not specified") + "</td></tr>" +
                "<tr><td style='padding: 8px 0; font-weight: bold;'>Accommodation:</td><td>" + inquiry.getAccommodation() + "</td></tr>" +
                "<tr><td style='padding: 8px 0; font-weight: bold;'>Room:</td><td>" + (inquiry.getRoom() != null ? inquiry.getRoom() : "Any / Not specified") + "</td></tr>" +
                "<tr><td style='padding: 8px 0; font-weight: bold;'>Need Pickup:</td><td>" + inquiry.getPickup() + "</td></tr>" +
                "</table>" +
                
                "<h3 style='color: #1e4a6d; border-bottom: 1px solid #eee; padding-bottom: 5px; margin-top: 20px;'>Guest Message</h3>" +
                "<div style='background-color: #f9f9f9; padding: 15px; border-radius: 5px; font-style: italic;'>" +
                (inquiry.getMessage() != null && !inquiry.getMessage().isEmpty() ? inquiry.getMessage() : "No message provided.") +
                "</div>" +
                "</div>" +
                "<div style='background-color: #f4f4f4; color: #777; padding: 15px; text-align: center; font-size: 12px;'>" +
                "This inquiry was sent from the Etili Village Booking System." +
                "</div>" +
                "</div>" +
                "</body></html>";

        try {
            emailService.sendBookingEmail(inquiry.getEmail(), adminEmail, subject, htmlBody);
            return ResponseEntity.ok("Booking inquiry sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send booking inquiry: " + e.getMessage());
        }
    }
}
