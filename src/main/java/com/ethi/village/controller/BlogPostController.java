package com.ethi.village.controller;

import com.ethi.village.dto.request.BlogPostRequest;
import com.ethi.village.entity.BlogPost;
import com.ethi.village.service.BlogPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/blogs")
@CrossOrigin(origins = "https://ethi-village-git-dev-oshadaovs-projects.vercel.app")
public class BlogPostController {

    private final BlogPostService service;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BlogPostController(BlogPostService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BlogPost create(
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        BlogPostRequest request = objectMapper.readValue(data, BlogPostRequest.class);
        return service.create(request, image);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BlogPost update(
            @PathVariable Long id,
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        BlogPostRequest request = objectMapper.readValue(data, BlogPostRequest.class);
        return service.update(id, request, image);
    }

    @GetMapping
    public List<BlogPost> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/slug/{slug}")
    public BlogPost getBySlug(@PathVariable String slug) {
        return service.getBySlug(slug);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return "Deleted successfully";
    }
}
