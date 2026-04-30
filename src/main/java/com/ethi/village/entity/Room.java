package com.ethi.village.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String guests;
    private String priceText;
    private Integer pricePerNight;
    private Integer minNights;

    @Column(unique = true)
    private String imageKey;

    // frontend expects "image"
    private String image;

    private String imagePublicId;

    @Column(length = 3000)
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "room_amenities",
            joinColumns = @JoinColumn(name = "room_id")
    )
    @Column(name = "amenity")
    private List<String> amenities;

    @ElementCollection
    @CollectionTable(
            name = "room_highlights",
            joinColumns = @JoinColumn(name = "room_id")
    )
    @Column(name = "highlight")
    private List<String> highlights;

    @ElementCollection
    @CollectionTable(
            name = "room_meals_included",
            joinColumns = @JoinColumn(name = "room_id")
    )
    @Column(name = "meal")
    private List<String> mealsIncluded;

    @ElementCollection
    @CollectionTable(
            name = "room_staff_services",
            joinColumns = @JoinColumn(name = "room_id")
    )
    @Column(name = "service")
    private List<String> staffServices;

    @ElementCollection
    @CollectionTable(
            name = "room_gallery_images",
            joinColumns = @JoinColumn(name = "room_id")
    )
    @Column(name = "image_url", length = 1000)
    private List<String> galleryImages;

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public List<String> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }

    public Integer getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(Integer pricePerNight) { this.pricePerNight = pricePerNight; }

    public Integer getMinNights() { return minNights; }
    public void setMinNights(Integer minNights) { this.minNights = minNights; }

    public List<String> getMealsIncluded() { return mealsIncluded; }
    public void setMealsIncluded(List<String> mealsIncluded) { this.mealsIncluded = mealsIncluded; }

    public List<String> getStaffServices() { return staffServices; }
    public void setStaffServices(List<String> staffServices) { this.staffServices = staffServices; }

    public List<String> getGalleryImages() { return galleryImages; }
    public void setGalleryImages(List<String> galleryImages) { this.galleryImages = galleryImages; }
}