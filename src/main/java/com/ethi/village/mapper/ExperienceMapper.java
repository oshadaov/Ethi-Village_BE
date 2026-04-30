package com.ethi.village.mapper;

import com.ethi.village.dto.response.ExperienceResponse;
import com.ethi.village.entity.Experience;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ExperienceMapper {

    public ExperienceResponse toResponse(Experience entity) {
        ExperienceResponse response = new ExperienceResponse();
        response.setId(entity.getId());
        response.setSlug(entity.getSlug());
        response.setImageKey(entity.getImageKey());
        response.setTitle(entity.getTitle());
        response.setCategory(entity.getCategory());
        response.setDuration(entity.getDuration());
        response.setGroupType(entity.getGroupType());
        response.setDifficulty(entity.getDifficulty());
        response.setPriceText(entity.getPriceText());
        response.setImageUrl(entity.getImageUrl());
        response.setImagePublicId(entity.getImagePublicId());
        response.setShortDescription(entity.getShortDescription());
        response.setDescription(entity.getDescription());

        // Wrap in new ArrayList to force Hibernate to load the collection
        // while the session is still open, instead of copying the lazy proxy
        response.setHighlights(entity.getHighlights() != null ? new ArrayList<>(entity.getHighlights()) : new ArrayList<>());
        response.setIncludes(entity.getIncludes()   != null ? new ArrayList<>(entity.getIncludes())   : new ArrayList<>());
        response.setBestFor(entity.getBestFor()     != null ? new ArrayList<>(entity.getBestFor())     : new ArrayList<>());
        response.setGalleryImages(entity.getGalleryImages() != null ? new ArrayList<>(entity.getGalleryImages()) : new ArrayList<>());

        return response;
    }
}