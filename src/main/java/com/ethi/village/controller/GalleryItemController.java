package com.ethi.village.controller;

import com.ethi.village.dto.request.GalleryItemRequest;
import com.ethi.village.entity.GalleryItem;
import com.ethi.village.service.GalleryItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/gallery")
public class GalleryItemController {

    private final GalleryItemService service;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GalleryItemController(GalleryItemService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GalleryItem create(
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        GalleryItemRequest request = objectMapper.readValue(data, GalleryItemRequest.class);
        return service.create(request, image);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GalleryItem update(
            @PathVariable Long id,
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        GalleryItemRequest request = objectMapper.readValue(data, GalleryItemRequest.class);
        return service.update(id, request, image);
    }

    @GetMapping
    public List<GalleryItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GalleryItem getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return "Gallery item deleted successfully";
    }
}