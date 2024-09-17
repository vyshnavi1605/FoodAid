package com.FoodAid.Group35.repository;

import com.FoodAid.Group35.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import com.FoodAid.Group35.entity.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query("select u from Food u where u.foodID = :foodId")
    public Food fetchByFoodID(@Param("foodId") int foodId);

    @Query("select u from Food u where u.status = :status")
    public List<Food> fetchAllActiveFoodItems(@Param("status") String status);

    @Query("select u from Food u where u.acceptedUser = :userName or u.postedUser = :userName")
    public List<Food> fetchAllClientItems(String userName);
}
