package com.sliit.ds.FastFoodOnlineIT16032798.service;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Food;

import java.util.List;

public interface FoodService {

    Food findById(String fId);

    Food findByName(String name);

    List<Food> findFoodContainingName(String name);

    double getPriceOf(String fId);

    void saveFood(Food food);

    void updateFood(Food foodUpdate);

    void deleteFoodById(String name);

    List<Food> findAllFood();

    public boolean isFoodExist(Food food);
}
