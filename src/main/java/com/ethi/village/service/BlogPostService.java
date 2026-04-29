package com.ethi.village.service;

import com.ethi.village.dto.request.BlogPostRequest;
import com.ethi.village.entity.BlogPost;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogPostService {
    BlogPost create(BlogPostRequest request, MultipartFile image) throws IOException;
    BlogPost update(Long id, BlogPostRequest request, MultipartFile image) throws IOException;
    List<BlogPost> getAll();
    BlogPost getById(Long id);
    BlogPost getBySlug(String slug);
    void delete(Long id) throws IOException;
}