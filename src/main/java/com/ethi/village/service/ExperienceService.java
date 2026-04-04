package com.ethi.village.service;

import com.ethi.village.dto.request.ExperienceRequest;
import com.ethi.village.entity.Experience;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExperienceService {

    Experience create(ExperienceRequest request, MultipartFile image) throws IOException;

    Experience update(Long id, ExperienceRequest request, MultipartFile image) throws IOException;

    Experience getById(Long id);

    List<Experience> getAll();

    void delete(Long id) throws IOException;
}