package com.ethi.village.service.impl;

import com.ethi.village.dto.request.RoomRequest;
import com.ethi.village.dto.response.RoomResponse;
import com.ethi.village.entity.Room;
import com.ethi.village.repository.RoomRepository;
import com.ethi.village.service.CloudinaryService;
import com.ethi.village.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repository;
    private final CloudinaryService cloudinaryService;

    public RoomServiceImpl(RoomRepository repository, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    @Transactional
    public RoomResponse create(RoomRequest request, MultipartFile image) throws IOException {
        Room room = new Room();
        applyData(room, request);

        if (image != null && !image.isEmpty()) {
            Map upload = cloudinaryService.upload(image);
            room.setImage((String) upload.get("secure_url"));
            room.setImagePublicId((String) upload.get("public_id"));
        }

        return toResponse(repository.save(room));
    }

    @Override
    @Transactional
    public RoomResponse update(Long id, RoomRequest request, MultipartFile image) throws IOException {
        Room room = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        applyData(room, request);

        if (image != null && !image.isEmpty()) {
            if (room.getImagePublicId() != null) {
                cloudinaryService.deleteImage(room.getImagePublicId());
            }
            Map upload = cloudinaryService.upload(image);
            room.setImage((String) upload.get("secure_url"));
            room.setImagePublicId((String) upload.get("public_id"));
        }

        return toResponse(repository.save(room));
    }

    @Override
    @Transactional(readOnly = true)
    public RoomResponse getById(Long id) {
        Room room = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        return toResponse(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws IOException {
        Room room = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.getImagePublicId() != null) {
            cloudinaryService.deleteImage(room.getImagePublicId());
        }

        repository.delete(room);
    }

    private void applyData(Room room, RoomRequest request) {
        room.setName(request.getName());
        room.setType(request.getType());
        room.setGuests(request.getGuests());
        room.setPriceText(request.getPriceText());
        room.setPricePerNight(request.getPricePerNight());
        room.setMinNights(request.getMinNights());
        room.setImageKey(request.getImageKey());
        room.setDescription(request.getDescription());
        room.setAmenities(request.getAmenities());
        room.setHighlights(request.getHighlights());
        room.setMealsIncluded(request.getMealsIncluded());
        room.setStaffServices(request.getStaffServices());
    }

    private RoomResponse toResponse(Room room) {
        RoomResponse r = new RoomResponse();
        r.setId(room.getId());
        r.setName(room.getName());
        r.setType(room.getType());
        r.setGuests(room.getGuests());
        r.setPriceText(room.getPriceText());
        r.setPricePerNight(room.getPricePerNight());
        r.setMinNights(room.getMinNights());
        r.setImageKey(room.getImageKey());
        r.setImage(room.getImage());
        r.setImagePublicId(room.getImagePublicId());
        r.setDescription(room.getDescription());
        r.setAmenities(room.getAmenities()      != null ? new ArrayList<>(room.getAmenities())      : new ArrayList<>());
        r.setHighlights(room.getHighlights()    != null ? new ArrayList<>(room.getHighlights())     : new ArrayList<>());
        r.setMealsIncluded(room.getMealsIncluded() != null ? new ArrayList<>(room.getMealsIncluded()) : new ArrayList<>());
        r.setStaffServices(room.getStaffServices() != null ? new ArrayList<>(room.getStaffServices()) : new ArrayList<>());
        return r;
    }
}