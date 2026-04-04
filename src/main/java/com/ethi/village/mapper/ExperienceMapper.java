package com.ethi.village.mapper;

import com.ethi.village.dto.response.ExperienceResponse;
import com.ethi.village.entity.Experience;
import org.springframework.stereotype.Component;

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
        response.setHighlights(entity.getHighlights());
        response.setIncludes(entity.getIncludes());
        response.setBestFor(entity.getBestFor());
        return response;
    }
}