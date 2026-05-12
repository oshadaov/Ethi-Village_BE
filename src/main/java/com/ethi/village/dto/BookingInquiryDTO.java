package com.ethi.village.dto;

import lombok.Data;

@Data
public class BookingInquiryDTO {
    private String fullName;
    private String email;
    private String phone;
    private String nationality;
    private String preferredDate;
    private String guests;
    private String experience;
    private String accommodation;
    private String pickup;
    private String message;
}
