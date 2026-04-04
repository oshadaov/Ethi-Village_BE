package com.ethi.village.repository;

import com.ethi.village.entity.SiteImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteImageRepository extends JpaRepository<SiteImage, Long> {
    Optional<SiteImage> findByImageKey(String imageKey);
    void deleteByImageKey(String imageKey);
    boolean existsByImageKey(String imageKey);
}