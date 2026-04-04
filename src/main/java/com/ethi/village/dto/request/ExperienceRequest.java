package com.ethi.village.dto.request;

import java.util.List;

public class ExperienceRequest {

    private String slug;
    private String imageKey;
    private String title;
    private String category;
    private String duration;
    private String groupType;
    private String difficulty;
    private String priceText;
    private String shortDescription;
    private List<String> highlights;
    private List<String> includes;
    private List<String> bestFor;

    public ExperienceRequest() {
    }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getImageKey() { return imageKey; }
    public void setImageKey(String imageKey) { this.imageKey = imageKey; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getGroupType() { return groupType; }
    public void setGroupType(String groupType) { this.groupType = groupType; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getPriceText() { return priceText; }
    public void setPriceText(String priceText) { this.priceText = priceText; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public List<String> getHighlights() { return highlights; }
    public void setHighlights(List<String> highlights) { this.highlights = highlights; }

    public List<String> getIncludes() { return includes; }
    public void setIncludes(List<String> includes) { this.includes = includes; }

    public List<String> getBestFor() { return bestFor; }
    public void setBestFor(List<String> bestFor) { this.bestFor = bestFor; }
}