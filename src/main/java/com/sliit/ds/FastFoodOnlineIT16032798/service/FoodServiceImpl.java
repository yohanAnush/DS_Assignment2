package com.sliit.ds.FastFoodOnlineIT16032798.service;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Food;
import com.sliit.ds.FastFoodOnlineIT16032798.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("foodService")
@Transactional
public class FoodServiceImpl implements  {
    @Autowired
    FoodRepository foodRepository;

    private static List<Food> foodItems;

    public void addFood(Food food) {
        foodRepository.save(food);
    }
}
