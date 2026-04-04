package com.ethi.village.service.impl;

import com.ethi.village.dto.request.ExperienceRequest;
import com.ethi.village.entity.Experience;
import com.ethi.village.repository.ExperienceRepository;
import com.ethi.village.service.CloudinaryService;
import com.ethi.village.service.ExperienceService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository repository;
    private final CloudinaryService cloudinaryService;

    public ExperienceServiceImpl(ExperienceRepository repository, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Experience create(ExperienceRequest request, MultipartFile image) throws IOException {

        Experience exp = new Experience();
        applyData(exp, request);

        if (image != null && !image.isEmpty()) {
            Map upload = cloudinaryService.upload(image);
            exp.setImageUrl((String) upload.get("secure_url"));
            exp.setImagePublicId((String) upload.get("public_id"));
        }

        return repository.save(exp);
    }

    @Override
    public Experience update(Long id, ExperienceRequest request, MultipartFile image) throws IOException {

        Experience exp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        applyData(exp, request);

        // Replace image if new one provided
        if (image != null && !image.isEmpty()) {

            // delete old image
            if (exp.getImagePublicId() != null) {
                cloudinaryService.deleteImage(exp.getImagePublicId());
            }

            Map upload = cloudinaryService.upload(image);
            exp.setImageUrl((String) upload.get("secure_url"));
            exp.setImagePublicId((String) upload.get("public_id"));
        }

        return repository.save(exp);
    }

    @Override
    public Experience getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));
    }

    @Override
    public List<Experience> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) throws IOException {

        Experience exp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        // delete image from Cloudinary
        if (exp.getImagePublicId() != null) {
            cloudinaryService.deleteImage(exp.getImagePublicId());
        }

        repository.delete(exp);
    }

    private void applyData(Experience exp, ExperienceRequest request) {
        exp.setSlug(request.getSlug());
        exp.setImageKey(request.getImageKey());
        exp.setTitle(request.getTitle());
        exp.setCategory(request.getCategory());
        exp.setDuration(request.getDuration());
        exp.setGroupType(request.getGroupType());
        exp.setDifficulty(request.getDifficulty());
        exp.setPriceText(request.getPriceText());
        exp.setShortDescription(request.getShortDescription());
        exp.setHighlights(request.getHighlights());
        exp.setIncludes(request.getIncludes());
        exp.setBestFor(request.getBestFor());
    }
}