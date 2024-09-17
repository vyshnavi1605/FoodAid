package com.FoodAid.Group35.controller;

import com.FoodAid.Group35.exception.ImageUploadException;
import com.FoodAid.Group35.services.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping(value = "/uploadProfileImage")
    public String addOrUpdateProfileImage(@RequestParam("image") MultipartFile image, @RequestParam("userId") Long userId) throws ImageUploadException {
        return imageUploadService.addOrUpdateProfileImage(image, userId);
    }

    @GetMapping(value = "/getProfileImage/{userId}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<?> getProfileImage(@PathVariable("userId") Long userId) throws ImageUploadException, IOException {

        byte[] image = imageUploadService.getProfileImage(userId);
        String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));

        if (contentType.contains("png")) {

            return ResponseEntity.ok()
                    .contentLength(image.length)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new InputStreamResource(new ByteArrayInputStream(image)));

        } else if (contentType.contains("jpeg")) {
            return ResponseEntity.ok()
                    .contentLength(image.length)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(new ByteArrayInputStream(image)));

        } else {
            return ResponseEntity.notFound().build();
        }



    }

}
