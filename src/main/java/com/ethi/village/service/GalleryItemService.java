package com.ethi.village.service;

import com.ethi.village.dto.request.GalleryItemRequest;
import com.ethi.village.entity.GalleryItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GalleryItemService {

    GalleryItem create(GalleryItemRequest request, MultipartFile image) throws IOException;

    GalleryItem update(Long id, GalleryItemRequest request, MultipartFile image) throws IOException;

    GalleryItem getById(Long id);

    List<GalleryItem> getAll();

    void delete(Long id) throws IOException;
}