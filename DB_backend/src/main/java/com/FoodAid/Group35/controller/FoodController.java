package com.FoodAid.Group35.controller;

import com.FoodAid.Group35.entity.Food;
import com.FoodAid.Group35.model.FoodBean;
import com.FoodAid.Group35.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FoodAid.Group35.services.FoodService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/posting") This is needed as I used authorities
@RestController
public class FoodController {

	@Autowired
	FoodService foodService;

	FoodRepository foodRepository;

	public FoodController(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}

	@PostMapping("auth/saveFood/{username}")
	public ResponseEntity<String> saveFood(@RequestBody FoodBean foodBean, @PathVariable("username") String username){
		foodService.saveFood(foodBean, username);
		return new ResponseEntity<>("Successfully Saved", HttpStatus.ACCEPTED);
	}

	@GetMapping("auth/getAllItems")
	public List<FoodBean> getAllItems() {
		return foodService.fetchAllFoodItems();
	}

	@DeleteMapping("auth/removeFood/{foodID}/{username}")
	public ResponseEntity<String> deleteFood(@PathVariable("foodID") int foodID, @PathVariable("username") String username){
		try {
			return new ResponseEntity<>(foodService.acceptListing(foodID, username), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity(e.getCause(),HttpStatus.BAD_REQUEST);
		}
	}
}
