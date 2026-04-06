package com.ethi.village.controller;

import com.ethi.village.dto.request.ExperienceRequest;
import com.ethi.village.entity.Experience;
import com.ethi.village.service.ExperienceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/experiences")
@CrossOrigin(origins = "https://ethi-village-git-dev-oshadaovs-projects.vercel.app")
public class ExperienceController {

    private final ExperienceService service;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ExperienceController(ExperienceService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Experience create(
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {

        ExperienceRequest request = objectMapper.readValue(data, ExperienceRequest.class);
        return service.create(request, image);
    }

    // UPDATE
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Experience update(
            @PathVariable Long id,
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {

        ExperienceRequest request = objectMapper.readValue(data, ExperienceRequest.class);
        return service.update(id, request, image);
    }

    // GET ALL
    @GetMapping
    public List<Experience> getAll() {
        return service.getAll();
    }

    // GET ONE
    @GetMapping("/{id}")
    public Experience getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return "Deleted successfully";
    }
}