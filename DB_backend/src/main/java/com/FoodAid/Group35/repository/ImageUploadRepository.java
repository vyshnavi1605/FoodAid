package com.FoodAid.Group35.repository;

import com.FoodAid.Group35.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageUploadRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByUserId(Long userId);

}
