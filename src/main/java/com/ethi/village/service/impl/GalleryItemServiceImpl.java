package com.ethi.village.service.impl;

import com.ethi.village.dto.request.GalleryItemRequest;
import com.ethi.village.entity.GalleryItem;
import com.ethi.village.repository.GalleryItemRepository;
import com.ethi.village.service.CloudinaryService;
import com.ethi.village.service.GalleryItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class GalleryItemServiceImpl implements GalleryItemService {

    private final GalleryItemRepository repository;
    private final CloudinaryService cloudinaryService;

    public GalleryItemServiceImpl(GalleryItemRepository repository, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public GalleryItem create(GalleryItemRequest request, MultipartFile image) throws IOException {
        GalleryItem item = new GalleryItem();
        applyData(item, request);

        if (image != null && !image.isEmpty()) {
            Map upload = cloudinaryService.upload(image);
            item.setImage((String) upload.get("secure_url"));
            item.setImagePublicId((String) upload.get("public_id"));
        }

        return repository.save(item);
    }

    @Override
    public GalleryItem update(Long id, GalleryItemRequest request, MultipartFile image) throws IOException {
        GalleryItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery item not found"));

        applyData(item, request);

        if (image != null && !image.isEmpty()) {
            if (item.getImagePublicId() != null) {
                cloudinaryService.deleteImage(item.getImagePublicId());
            }

            Map upload = cloudinaryService.upload(image);
            item.setImage((String) upload.get("secure_url"));
            item.setImagePublicId((String) upload.get("public_id"));
        }

        return repository.save(item);
    }

    @Override
    public GalleryItem getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery item not found"));
    }

    @Override
    public List<GalleryItem> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) throws IOException {
        GalleryItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery item not found"));

        if (item.getImagePublicId() != null) {
            cloudinaryService.deleteImage(item.getImagePublicId());
        }

        repository.delete(item);
    }

    private void applyData(GalleryItem item, GalleryItemRequest request) {
        item.setTitle(request.getTitle());
        item.setCategory(request.getCategory());
        item.setImageKey(request.getImageKey());
        item.setAlt(request.getAlt());
        item.setDescription(request.getDescription());
    }
}