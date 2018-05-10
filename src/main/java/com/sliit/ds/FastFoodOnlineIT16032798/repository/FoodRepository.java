package com.sliit.ds.FastFoodOnlineIT16032798.repository;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, String> {

    Food findFoodByName(final String name);
    Food findFoodByFId(final String fId);

    void deleteFoodByFId(final String fId);
    void deleteFoodByName(final String name);

}
