package com.ethi.village.service.impl;

import com.ethi.village.dto.request.BlogPostRequest;
import com.ethi.village.entity.BlogPost;
import com.ethi.village.repository.BlogPostRepository;
import com.ethi.village.service.BlogPostService;
import com.ethi.village.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository repository;
    private final CloudinaryService cloudinaryService;

    public BlogPostServiceImpl(BlogPostRepository repository, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public BlogPost create(BlogPostRequest request, MultipartFile image) throws IOException {
        BlogPost post = new BlogPost();
        post.setTitle(request.getTitle());
        post.setSlug(request.getSlug());
        post.setAuthor(request.getAuthor());
        post.setContent(request.getContent());
        post.setShortDescription(request.getShortDescription());
        post.setImageKey(request.getImageKey());
        post.setPublishedDate(LocalDateTime.now());

        if (image != null && !image.isEmpty()) {
            Map uploadResult = cloudinaryService.upload(image);
            post.setImageUrl((String) uploadResult.get("url"));
            post.setImagePublicId((String) uploadResult.get("public_id"));
        }

        return repository.save(post);
    }

    @Override
    public BlogPost update(Long id, BlogPostRequest request, MultipartFile image) throws IOException {
        BlogPost post = getById(id);

        post.setTitle(request.getTitle());
        post.setSlug(request.getSlug());
        post.setAuthor(request.getAuthor());
        post.setContent(request.getContent());
        post.setShortDescription(request.getShortDescription());
        post.setImageKey(request.getImageKey());

        if (image != null && !image.isEmpty()) {
            if (post.getImagePublicId() != null) {
                cloudinaryService.deleteImage(post.getImagePublicId());
            }
            Map uploadResult = cloudinaryService.upload(image);
            post.setImageUrl((String) uploadResult.get("url"));
            post.setImagePublicId((String) uploadResult.get("public_id"));
        }

        return repository.save(post);
    }

    @Override
    public List<BlogPost> getAll() {
        return repository.findAll();
    }

    @Override
    public BlogPost getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Blog post not found"));
    }

    @Override
    public BlogPost getBySlug(String slug) {
        return repository.findBySlug(slug).orElseThrow(() -> new RuntimeException("Blog post not found"));
    }

    @Override
    public void delete(Long id) throws IOException {
        BlogPost post = getById(id);
        if (post.getImagePublicId() != null) {
            cloudinaryService.deleteImage(post.getImagePublicId());
        }
        repository.delete(post);
    }
}
