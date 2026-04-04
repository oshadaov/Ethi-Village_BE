package com.ethi.village.service;

import com.ethi.village.dto.response.SiteImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SiteImageService {

    List<SiteImageResponse> getAll();

    SiteImageResponse upload(String imageKey, MultipartFile file) throws IOException;

    SiteImageResponse deleteByImageKey(String imageKey) throws IOException;
}