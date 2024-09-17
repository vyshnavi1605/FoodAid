package com.FoodAid.Group35.services;

import javax.transaction.Transactional;

import com.FoodAid.Group35.model.FoodBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FoodAid.Group35.entity.Food;
import com.FoodAid.Group35.repository.FoodRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FoodService {

	@Autowired
	FoodRepository foodRepository;

	@Autowired
	FoodBean foodBean;

	public FoodService(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}
	
	public void saveFood(FoodBean foodBean, String username) {
		Food foodEntity = new Food();
		foodBean.setStatus("Active");
		foodBean.setItemAddedDate(new Date());
		BeanUtils.copyProperties(foodBean,foodEntity);
		foodEntity.setPostedUser(username);
		foodRepository.saveAndFlush(foodEntity);
	}

	public String acceptListing(int foodID, String username)  {
		try {
			Food foodEntity = foodRepository.fetchByFoodID(foodID);
			foodEntity.setFlag("Accepted");
			foodEntity.setStatus("Deleted");
			foodEntity.setAcceptedUser(username);
			foodEntity.setItemAcceptedDate(new Date());
			foodRepository.save(foodEntity);
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
		return "Accepted food saved";
	}

	public List<FoodBean> fetchAllFoodItems() {
		List<Food> foodEntityList = foodRepository.fetchAllActiveFoodItems("Active");
		List<FoodBean> foodBeanList = new ArrayList<>();
		//FoodBean foodBean = new FoodBean();
		for (Food food : foodEntityList) {
			BeanUtils.copyProperties(food, foodBean);
			foodBeanList.add(new FoodBean(foodBean.getFoodID(), foodBean.getFoodName(), foodBean.getCompanyName(),
					foodBean.getNumberOfItems(), foodBean.getItemAddedDate(),
					foodBean.getItemAcceptedDate(), foodBean.getStatus(), foodBean.getFlag()));
		}
		return foodBeanList;
	}
}
