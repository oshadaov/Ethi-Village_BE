package com.ethi.village.controller;

import com.ethi.village.dto.request.ContactInquiryRequest;
import com.ethi.village.entity.ContactInquiry;
import com.ethi.village.service.ContactInquiryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/contact-inquiries")
public class ContactInquiryController {

    private final ContactInquiryService service;

    public ContactInquiryController(ContactInquiryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactInquiry submitInquiry(@Valid @RequestBody ContactInquiryRequest request) {
        return service.save(request);
    }

    @GetMapping
    public List<ContactInquiry> getAllInquiries() {
        return service.getAll();
    }
}
