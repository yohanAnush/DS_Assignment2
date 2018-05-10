package com.sliit.ds.FastFoodOnlineIT16032798.repository;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, String> {

    Food findFoodByName(String name);
    Food findFoodByFId(String fId);
    List<Food> findFoodsByName(String name);
    List<Food> findFoodByNameContaining(String name);

    void deleteFoodByFId(String fId);
    void deleteFoodByName(String name);

}
