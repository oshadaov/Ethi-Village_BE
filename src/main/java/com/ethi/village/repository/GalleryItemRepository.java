package com.ethi.village.repository;

import com.ethi.village.entity.GalleryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryItemRepository extends JpaRepository<GalleryItem, Long> {
}