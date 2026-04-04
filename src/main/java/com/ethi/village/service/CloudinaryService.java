package com.ethi.village.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {
    Map upload(MultipartFile file) throws IOException;
    void deleteImage(String publicId) throws IOException;
}