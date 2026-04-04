package com.ethi.village.service;

import com.ethi.village.dto.request.RoomRequest;
import com.ethi.village.entity.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomService {

    Room create(RoomRequest request, MultipartFile image) throws IOException;

    Room update(Long id, RoomRequest request, MultipartFile image) throws IOException;

    Room getById(Long id);

    List<Room> getAll();

    void delete(Long id) throws IOException;
}