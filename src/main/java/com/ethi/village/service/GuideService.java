package com.ethi.village.service;

import com.ethi.village.dto.request.GuideRequest;
import com.ethi.village.entity.Guide;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GuideService {

    Guide create(GuideRequest request, MultipartFile image) throws IOException;

    Guide update(Long id, GuideRequest request, MultipartFile image) throws IOException;

    Guide getById(Long id);

    List<Guide> getAll();

    void delete(Long id) throws IOException;
}