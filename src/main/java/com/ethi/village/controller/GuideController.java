package com.ethi.village.controller;

import com.ethi.village.dto.request.GuideRequest;
import com.ethi.village.entity.Guide;
import com.ethi.village.service.GuideService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/guides")
public class GuideController {

    private final GuideService service;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GuideController(GuideService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Guide create(
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        GuideRequest request = objectMapper.readValue(data, GuideRequest.class);
        return service.create(request, image);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Guide update(
            @PathVariable Long id,
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        GuideRequest request = objectMapper.readValue(data, GuideRequest.class);
        return service.update(id, request, image);
    }

    @GetMapping
    public List<Guide> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Guide getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return "Guide deleted successfully";
    }
}