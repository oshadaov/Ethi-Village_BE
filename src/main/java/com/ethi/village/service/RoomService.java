package com.ethi.village.service;

import com.ethi.village.dto.request.RoomRequest;
import com.ethi.village.dto.response.RoomResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomService {

    RoomResponse create(RoomRequest request, MultipartFile image) throws IOException;

    RoomResponse update(Long id, RoomRequest request, MultipartFile image) throws IOException;

    RoomResponse getById(Long id);

    List<RoomResponse> getAll();

    void delete(Long id) throws IOException;
}