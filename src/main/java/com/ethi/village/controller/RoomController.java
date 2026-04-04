package com.ethi.village.controller;

import com.ethi.village.dto.request.RoomRequest;
import com.ethi.village.entity.Room;
import com.ethi.village.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/rooms")
public class RoomController {

    private final RoomService service;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Room create(
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        RoomRequest request = objectMapper.readValue(data, RoomRequest.class);
        return service.create(request, image);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Room update(
            @PathVariable Long id,
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        RoomRequest request = objectMapper.readValue(data, RoomRequest.class);
        return service.update(id, request, image);
    }

    @GetMapping
    public List<Room> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Room getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return "Room deleted successfully";
    }
}