package com.ethi.village.service;

import com.ethi.village.dto.request.ExperienceRequest;
import com.ethi.village.dto.response.ExperienceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExperienceService {

    ExperienceResponse create(ExperienceRequest request, MultipartFile image) throws IOException;

    ExperienceResponse update(Long id, ExperienceRequest request, MultipartFile image) throws IOException;

    ExperienceResponse getById(Long id);

    List<ExperienceResponse> getAll();

    void delete(Long id) throws IOException;
}