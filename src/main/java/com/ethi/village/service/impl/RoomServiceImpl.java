package com.ethi.village.service.impl;

import com.ethi.village.dto.request.RoomRequest;
import com.ethi.village.entity.Room;
import com.ethi.village.repository.RoomRepository;
import com.ethi.village.service.CloudinaryService;
import com.ethi.village.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repository;
    private final CloudinaryService cloudinaryService;

    public RoomServiceImpl(RoomRepository repository, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Room create(RoomRequest request, MultipartFile image) throws IOException {
        Room room = new Room();
        applyData(room, request);

        if (image != null && !image.isEmpty()) {
            Map upload = cloudinaryService.upload(image);
            room.setImage((String) upload.get("secure_url"));
            room.setImagePublicId((String) upload.get("public_id"));
        }

        return repository.save(room);
    }

    @Override
    public Room update(Long id, RoomRequest request, MultipartFile image) throws IOException {
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

        return repository.save(room);
    }

    @Override
    public Room getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public List<Room> getAll() {
        return repository.findAll();
    }

    @Override
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
}