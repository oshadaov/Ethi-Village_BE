package com.ethi.village.dto.response;

import java.util.List;

public class RoomResponse {

    private Long id;
    private String name;
    private String type;
    private String guests;
    private String priceText;
    private Integer pricePerNight;
    private Integer minNights;
    private String imageKey;
    private String image;
    private String imagePublicId;
    private String description;
    private List<String> amenities;
    private List<String> highlights;
    private List<String> mealsIncluded;
    private List<String> staffServices;
    private List<String> galleryImages;

    public RoomResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getGuests() { return guests; }
    public void setGuests(String guests) { this.guests = guests; }

    public String getPriceText() { return priceText; }
    public void setPriceText(String priceText) { this.priceText = priceText; }

    public Integer getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(Integer pricePerNight) { this.pricePerNight = pricePerNight; }

    public Integer getMinNights() { return minNights; }
    public void setMinNights(Integer minNights) { this.minNights = minNights; }

    public String getImageKey() { return imageKey; }
    public void setImageKey(String imageKey) { this.imageKey = imageKey; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getImagePublicId() { return imagePublicId; }
    public void setImagePublicId(String imagePublicId) { this.imagePublicId = imagePublicId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }

    public List<String> getHighlights() { return highlights; }
    public void setHighlights(List<String> highlights) { this.highlights = highlights; }

    public List<String> getMealsIncluded() { return mealsIncluded; }
    public void setMealsIncluded(List<String> mealsIncluded) { this.mealsIncluded = mealsIncluded; }

    public List<String> getStaffServices() { return staffServices; }
    public void setStaffServices(List<String> staffServices) { this.staffServices = staffServices; }

    public List<String> getGalleryImages() { return galleryImages; }
    public void setGalleryImages(List<String> galleryImages) { this.galleryImages = galleryImages; }
}