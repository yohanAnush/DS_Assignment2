package com.sliit.ds.FastFoodOnlineIT16032798.service;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Food;
import com.sliit.ds.FastFoodOnlineIT16032798.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("foodService")
@Transactional
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    private static List<Food> foodItems;

    public void addFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public Food findById(String fId) {
        return foodRepository.findFoodByFId(fId);
    }

    @Override
    public Food findByName(String name) {
        return foodRepository.findFoodByName(name);
    }
    
    public void saveFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void updateFood(Food foodUpdate) {
        //foodRepository.

    }

    @Override
    public void deleteFoodById(String name) {
        foodRepository.deleteFoodByName(name);
    }

    @Override
    public Iterable<Food> findAllFood() {
        return foodRepository.findAll();
    }


    @Override
    public boolean isFoodExist(Food food) {
        return foodRepository.existsById(food.getfId());
    }
}
