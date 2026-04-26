package com.ethi.village.service;

import com.ethi.village.dto.request.ContactInquiryRequest;
import com.ethi.village.entity.ContactInquiry;

import java.util.List;

public interface ContactInquiryService {
    ContactInquiry save(ContactInquiryRequest request);
    List<ContactInquiry> getAll();
}
