package com.ethi.village.service.impl;

import com.ethi.village.dto.response.SiteImageResponse;
import com.ethi.village.entity.SiteImage;
import com.ethi.village.repository.SiteImageRepository;
import com.ethi.village.service.CloudinaryService;
import com.ethi.village.service.SiteImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SiteImageServiceImpl implements SiteImageService {

    private final SiteImageRepository siteImageRepository;
    private final CloudinaryService cloudinaryService;

    public SiteImageServiceImpl(SiteImageRepository siteImageRepository, CloudinaryService cloudinaryService) {
        this.siteImageRepository = siteImageRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SiteImageResponse> getAll() {
        return siteImageRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public SiteImageResponse upload(String imageKey, MultipartFile file) throws IOException {
        if (imageKey == null || imageKey.isBlank()) {
            throw new RuntimeException("imageKey is required");
        }
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("image file is required");
        }

        SiteImage siteImage = siteImageRepository.findByImageKey(imageKey)
                .orElseGet(SiteImage::new);

        if (siteImage.getImagePublicId() != null && !siteImage.getImagePublicId().isBlank()) {
            cloudinaryService.deleteImage(siteImage.getImagePublicId());
        }

        Map uploadResult = cloudinaryService.upload(file);

        siteImage.setImageKey(imageKey);
        siteImage.setImageUrl((String) uploadResult.get("secure_url"));
        siteImage.setImagePublicId((String) uploadResult.get("public_id"));
        siteImage.setFileName(file.getOriginalFilename());
        siteImage.setFileType(file.getContentType());
        siteImage.setFileSize(file.getSize());

        return mapToResponse(siteImageRepository.save(siteImage));
    }

    @Override
    @Transactional
    public SiteImageResponse deleteByImageKey(String imageKey) throws IOException {
        SiteImage siteImage = siteImageRepository.findByImageKey(imageKey)
                .orElseThrow(() -> new RuntimeException("Site image not found for key: " + imageKey));

        if (siteImage.getImagePublicId() != null && !siteImage.getImagePublicId().isBlank()) {
            cloudinaryService.deleteImage(siteImage.getImagePublicId());
        }

        SiteImageResponse response = mapToResponse(siteImage);
        siteImageRepository.delete(siteImage);
        return response;
    }

    private SiteImageResponse mapToResponse(SiteImage siteImage) {
        return new SiteImageResponse(
                siteImage.getId(),
                siteImage.getImageKey(),
                siteImage.getImageUrl(),
                siteImage.getImagePublicId(),
                siteImage.getFileName(),
                siteImage.getFileType(),
                siteImage.getFileSize()
        );
    }
}