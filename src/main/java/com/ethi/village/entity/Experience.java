package com.ethi.village.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;

    @Column(unique = true)
    private String imageKey;

    private String title;
    private String category;
    private String duration;
    private String groupType;
    private String difficulty;
    private String priceText;

    private String image;
    private String imagePublicId;

    @Column(length = 3000)
    private String shortDescription;

    @Column(length = 5000)
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "experience_highlights",
            joinColumns = @JoinColumn(name = "experience_id")
    )
    @Column(name = "highlight")
    private List<String> highlights;

    @ElementCollection
    @CollectionTable(
            name = "experience_includes",
            joinColumns = @JoinColumn(name = "experience_id")
    )
    @Column(name = "include_item")
    private List<String> includes;

    @ElementCollection
    @CollectionTable(
            name = "experience_best_for",
            joinColumns = @JoinColumn(name = "experience_id")
    )
    @Column(name = "best_for")
    private List<String> bestFor;

    @ElementCollection
    @CollectionTable(
            name = "experience_gallery_images",
            joinColumns = @JoinColumn(name = "experience_id")
    )
    @Column(name = "image_url", length = 1000)
    private List<String> galleryImages;

    public Experience() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }

    public List<String> getIncludes() {
        return includes;
    }

    public void setIncludes(List<String> includes) {
        this.includes = includes;
    }

    public List<String> getBestFor() {
        return bestFor;
    }

    public void setBestFor(List<String> bestFor) {
        this.bestFor = bestFor;
    }

    public List<String> getGalleryImages() {
        return galleryImages;
    }

    public void setGalleryImages(List<String> galleryImages) {
        this.galleryImages = galleryImages;
    }

    public String getImageUrl() {
    return image;
    }
}