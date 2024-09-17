package com.FoodAid.Group35.services;

import com.FoodAid.Group35.entity.Food;
import com.FoodAid.Group35.model.FoodBean;
import com.FoodAid.Group35.repository.FoodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReportService {

    @Autowired
    FoodBean foodBean;

    @Autowired
    FoodRepository foodRepository;


    public List<FoodBean> getClientReport(String userName) {
        List<Food> foodEntityList = foodRepository.fetchAllClientItems(userName);
        List<FoodBean> foodBeanList = new ArrayList<>();
        for (Food food : foodEntityList) {
            BeanUtils.copyProperties(food, foodBean);
            foodBeanList.add(new FoodBean(foodBean.getFoodID(), foodBean.getFoodName(), foodBean.getCompanyName(),
                    foodBean.getNumberOfItems(), foodBean.getItemAddedDate(),
                    foodBean.getItemAcceptedDate(), foodBean.getStatus(), foodBean.getFlag()));
        }
        return foodBeanList;
    }
}
