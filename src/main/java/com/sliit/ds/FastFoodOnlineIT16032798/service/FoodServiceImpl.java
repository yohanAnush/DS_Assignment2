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

    @Override
    public Food findById(String fId) {
        return foodRepository.findFoodByFId(fId);
    }

    @Override
    public Food findByName(String name) {
        return foodRepository.findFoodByName(name);
    }

    @Override
    public List<Food> findFoodContainingName(String name) {
        Iterable<Food> foodIterable = foodRepository.findFoodByNameContaining(name);
        List<Food> foodList = new ArrayList<>();

        foodIterable.forEach(foodList::add);
        return foodList;
    }

    @Override
    public double getPriceOf(String fId) {
        Food food = foodRepository.findFoodByFId(fId);
        double price = -1;

        if (food != null) {
            price = food.getPrice();
        }

        return price;
    }

    public void saveFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void updateFood(Food foodUpdate) {
        Food foodExisting = findByName(foodUpdate.getName());

        // update the item we retrieved.
        // DON'T update the fId.
        if (foodExisting != null) {
            foodExisting.setIngredients(foodUpdate.getIngredients());
            foodExisting.setPrice(foodUpdate.getPrice());
            foodExisting.setPrice(foodUpdate.getPrice());

            saveFood(foodExisting);
        }
    }

    @Override
    public void deleteFoodById(String name) {
        foodRepository.deleteFoodByName(name);
    }

    @Override
    public List<Food> findAllFood() {
        return foodRepository.findAll();
    }


    @Override
    public boolean isFoodExist(Food food) {
        return foodRepository.existsById(food.getfId());
    }
}
