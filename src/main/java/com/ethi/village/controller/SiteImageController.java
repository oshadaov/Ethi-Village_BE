package com.ethi.village.controller;

import com.ethi.village.dto.response.SiteImageResponse;
import com.ethi.village.service.SiteImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/site-images")
public class SiteImageController {

    private final SiteImageService siteImageService;

    public SiteImageController(SiteImageService siteImageService) {
        this.siteImageService = siteImageService;
    }

    @GetMapping
    public List<SiteImageResponse> getSiteImages() {
        return siteImageService.getAll();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SiteImageResponse uploadSiteImage(
            @RequestPart("imageKey") String imageKey,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        return siteImageService.upload(imageKey, image);
    }

    @DeleteMapping("/{imageKey}")
    public SiteImageResponse deleteSiteImage(@PathVariable String imageKey) throws IOException {
        return siteImageService.deleteByImageKey(imageKey);
    }
}