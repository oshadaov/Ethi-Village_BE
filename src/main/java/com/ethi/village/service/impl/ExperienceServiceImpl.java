package com.ethi.village.service.impl;

import com.ethi.village.dto.request.ExperienceRequest;
import com.ethi.village.dto.response.ExperienceResponse;
import com.ethi.village.entity.Experience;
import com.ethi.village.mapper.ExperienceMapper;
import com.ethi.village.repository.ExperienceRepository;
import com.ethi.village.service.CloudinaryService;
import com.ethi.village.service.ExperienceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository repository;
    private final CloudinaryService cloudinaryService;
    private final ExperienceMapper mapper;

    public ExperienceServiceImpl(ExperienceRepository repository,
                                 CloudinaryService cloudinaryService,
                                 ExperienceMapper mapper) {
        this.repository = repository;
        this.cloudinaryService = cloudinaryService;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ExperienceResponse create(ExperienceRequest request, MultipartFile image) throws IOException {
        Experience exp = new Experience();
        applyData(exp, request);

        if (image != null && !image.isEmpty()) {
            Map upload = cloudinaryService.upload(image);
            exp.setImage((String) upload.get("secure_url"));
            exp.setImagePublicId((String) upload.get("public_id"));
        }

        return mapper.toResponse(repository.save(exp));
    }

    @Override
    @Transactional
    public ExperienceResponse update(Long id, ExperienceRequest request, MultipartFile image) throws IOException {
        Experience exp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        applyData(exp, request);

        if (image != null && !image.isEmpty()) {
            if (exp.getImagePublicId() != null) {
                cloudinaryService.deleteImage(exp.getImagePublicId());
            }
            Map upload = cloudinaryService.upload(image);
            exp.setImage((String) upload.get("secure_url"));
            exp.setImagePublicId((String) upload.get("public_id"));
        }

        return mapper.toResponse(repository.save(exp));
    }

    @Override
    @Transactional(readOnly = true)
    public ExperienceResponse getById(Long id) {
        Experience exp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));
        return mapper.toResponse(exp);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperienceResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws IOException {
        Experience exp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

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
        exp.setDescription(request.getDescription());
        exp.setHighlights(request.getHighlights());
        exp.setIncludes(request.getIncludes());
        exp.setBestFor(request.getBestFor());
        exp.setGalleryImages(request.getGalleryImages());
    }
}