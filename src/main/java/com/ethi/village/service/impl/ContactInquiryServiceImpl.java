package com.ethi.village.service.impl;

import com.ethi.village.dto.request.ContactInquiryRequest;
import com.ethi.village.entity.ContactInquiry;
import com.ethi.village.repository.ContactInquiryRepository;
import com.ethi.village.service.ContactInquiryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInquiryServiceImpl implements ContactInquiryService {

    private final ContactInquiryRepository repository;

    public ContactInquiryServiceImpl(ContactInquiryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ContactInquiry save(ContactInquiryRequest request) {
        ContactInquiry inquiry = new ContactInquiry();
        inquiry.setFullName(request.getFullName());
        inquiry.setEmail(request.getEmail());
        inquiry.setPhone(request.getPhone());
        inquiry.setNationality(request.getNationality());
        inquiry.setPreferredDate(request.getPreferredDate());
        inquiry.setGuests(request.getGuests());
        inquiry.setExperience(request.getExperience());
        inquiry.setAccommodation(request.getAccommodation());
        inquiry.setPickup(request.getPickup());
        inquiry.setMessage(request.getMessage());
        
        return repository.save(inquiry);
    }

    @Override
    public List<ContactInquiry> getAll() {
        return repository.findAllByOrderBySubmittedAtDesc();
    }
}
