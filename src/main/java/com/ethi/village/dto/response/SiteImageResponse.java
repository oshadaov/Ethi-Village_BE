package com.ethi.village.dto.response;

public class SiteImageResponse {

    private Long id;
    private String imageKey;
    private String imageUrl;
    private String imagePublicId;
    private String fileName;
    private String fileType;
    private Long fileSize;

    public SiteImageResponse() {
    }

    public SiteImageResponse(
            Long id,
            String imageKey,
            String imageUrl,
            String imagePublicId,
            String fileName,
            String fileType,
            Long fileSize
    ) {
        this.id = id;
        this.imageKey = imageKey;
        this.imageUrl = imageUrl;
        this.imagePublicId = imagePublicId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public Long getId() {
        return id;
    }

    public String getImageKey() {
        return imageKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImagePublicId() {
        return imagePublicId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}