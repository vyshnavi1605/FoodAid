package com.FoodAid.Group35.servicesTest;

import com.FoodAid.Group35.entity.Food;
import com.FoodAid.Group35.model.FoodBean;
import com.FoodAid.Group35.repository.FoodRepository;
import com.FoodAid.Group35.services.ReportService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.mock.mockito.SpyBeans;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@RunWith(SpringRunner.class)
@ActiveProfiles(value = {"test"})

public class ReportServiceTest {

    @Spy
    FoodBean foodBean;

    @Mock
    FoodRepository foodRepository;

    @InjectMocks
    ReportService reportService;

    @Before
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetClientReport() {
        List<Food> foodList = populateFoodData();

    Mockito.when(foodRepository.fetchAllClientItems(Mockito.anyString())).thenReturn(foodList);

        List<FoodBean> actualList = reportService.getClientReport(Mockito.anyString());
        assertEquals(2,actualList.size());

    }

    private List<Food> populateFoodData(){

      Food food = new Food();

        List<Food> foodEntityList = Stream.of(new Food(1, "Chicken", "Asda", 3, new Date(),
                        new Date(), "Deleted", "Accepted", "Vyshnavi", "Emily"),
                new Food(2, "Prawns", "Tesco", 5, new Date(),
                        new Date(), "Deleted", "Accepted", "Vyshnavi", "Emily")).
                collect(Collectors.toList());

        return foodEntityList;
    }
}
