package com.ethi.village.service.impl;

import com.ethi.village.dto.request.GuideRequest;
import com.ethi.village.entity.Guide;
import com.ethi.village.repository.GuideRepository;
import com.ethi.village.service.CloudinaryService;
import com.ethi.village.service.GuideService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class GuideServiceImpl implements GuideService {

    private final GuideRepository repository;
    private final CloudinaryService cloudinaryService;

    public GuideServiceImpl(GuideRepository repository, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Guide create(GuideRequest request, MultipartFile image) throws IOException {
        Guide guide = new Guide();
        applyData(guide, request);

        if (image != null && !image.isEmpty()) {
            Map upload = cloudinaryService.upload(image);
            guide.setImg((String) upload.get("secure_url"));
            guide.setImagePublicId((String) upload.get("public_id"));
        }

        return repository.save(guide);
    }

    @Override
    public Guide update(Long id, GuideRequest request, MultipartFile image) throws IOException {
        Guide guide = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guide not found"));

        applyData(guide, request);

        if (image != null && !image.isEmpty()) {
            if (guide.getImagePublicId() != null) {
                cloudinaryService.deleteImage(guide.getImagePublicId());
            }

            Map upload = cloudinaryService.upload(image);
            guide.setImg((String) upload.get("secure_url"));
            guide.setImagePublicId((String) upload.get("public_id"));
        }

        return repository.save(guide);
    }

    @Override
    public Guide getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guide not found"));
    }

    @Override
    public List<Guide> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) throws IOException {
        Guide guide = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guide not found"));

        if (guide.getImagePublicId() != null) {
            cloudinaryService.deleteImage(guide.getImagePublicId());
        }

        repository.delete(guide);
    }

    private void applyData(Guide guide, GuideRequest request) {
        guide.setName(request.getName());
        guide.setRole(request.getRole());
        guide.setDescp(request.getDescp());
    }
}