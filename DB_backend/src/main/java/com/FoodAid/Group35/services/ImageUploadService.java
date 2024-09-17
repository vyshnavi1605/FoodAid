package com.FoodAid.Group35.services;

import com.FoodAid.Group35.exception.ImageUploadException;
import com.FoodAid.Group35.entity.Image;
import com.FoodAid.Group35.repository.ImageUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageUploadService {

    @Autowired
    private ImageUploadRepository imageUploadRepository;

    public String addOrUpdateProfileImage(MultipartFile image, Long userId) throws ImageUploadException {

        if (image.isEmpty()) {
            throw new ImageUploadException("Please upload an image, try again");
        }
        else if (userId == null) {
            throw new ImageUploadException("Please provide user ID, try again");
        }

        try {
            // convert multipart file to byte array
            byte[] byteObjects = new byte[image.getBytes().length];
            int i = 0;
            for (byte b : image.getBytes()){
                byteObjects[i++] = b;
            }

            // see if image already exists
            Optional<Image> databaseImage = imageUploadRepository.findByUserId(userId);

            if (databaseImage.isPresent()) {
                // use existing image. dont create a new one
                Image existingImage = databaseImage.get();
                existingImage.setId(existingImage.getId());
                existingImage.setImage(byteObjects);
                existingImage.setUserId(userId);
                imageUploadRepository.save(existingImage);
            } else {
                // if it doesnt exist then create new image in db
                Image newImage = new Image();
                newImage.setImage(byteObjects);
                newImage.setUserId(userId);
                imageUploadRepository.save(newImage);
            }

            // set successful response message
            return "Image Uploaded Successfully";

        } catch (IOException e) {
            return "Error occurred during image upload, please try again";
        }
    }

    public byte[] getProfileImage(Long userId) throws ImageUploadException {

        if (userId == null) {
            throw new ImageUploadException("Please provide user ID, try again");
        }

        // fetching image from db
        Optional<Image> databaseImage = imageUploadRepository.findByUserId(userId);

        // check to see if image contains a value, only valid user ids will return a value
        if (databaseImage.isPresent()) {
            return databaseImage.get().getImage();
        }
        throw new ImageUploadException("Invalid user ID, try again");

    }

}
