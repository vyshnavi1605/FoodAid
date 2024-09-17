package com.FoodAid.Group35.servicesTest;

import com.FoodAid.Group35.entity.Food;
import com.FoodAid.Group35.model.FoodBean;
import com.FoodAid.Group35.repository.FoodRepository;
import com.FoodAid.Group35.services.FoodService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@RunWith(SpringRunner.class)
@ActiveProfiles(value = {"test"})
public class FoodServiceTest {

    @Mock
    FoodRepository foodRepository;

    @InjectMocks
    FoodService foodService;

    @Spy
    Food food;

    @Spy
    FoodBean foodBean;

    @Before
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void acceptListingTest() {
        food = populateFood();

        Mockito.when(foodRepository.fetchByFoodID(Mockito.anyInt())).thenReturn(food);
        ArgumentCaptor<Food> foodArgumentCaptor = ArgumentCaptor.forClass(Food.class);
        Mockito.when(foodRepository.save(foodArgumentCaptor.capture())).thenAnswer(call -> call.getArgument(0, Food.class));
        foodService.acceptListing(1,Mockito.anyString());
        Food newFood = foodArgumentCaptor.getValue();
        assertEquals("Accepted", newFood.getFlag());
    }


    private Food populateFood() {
        food.setFoodName("Chicken");
        food.setCompanyName("Asda");
        food.setNumberOfItems(3);
        food.setItemAddedDate(new Date());
        food.setPostedUser("Emily");
        return food;
    }

    @Test
    public void saveFoodTest(){
        foodBean = postFood();

        ArgumentCaptor<Food> sfoodArgumentCaptor = ArgumentCaptor.forClass(Food.class);
        Mockito.when(foodRepository.saveAndFlush(sfoodArgumentCaptor.capture())).thenAnswer(call -> call.getArgument(0, Food.class));
        foodService.saveFood(foodBean,Mockito.anyString());
        Food sFood = sfoodArgumentCaptor.getValue();
        assertEquals("Active", sFood.getStatus());

    }
    private FoodBean postFood() {
        foodBean.setFoodName("Chicken");
        foodBean.setCompanyName("Asda");
        foodBean.setNumberOfItems(3);
        return foodBean;
    }

}
