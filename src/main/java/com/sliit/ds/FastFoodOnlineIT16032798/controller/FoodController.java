package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Food;
import com.sliit.ds.FastFoodOnlineIT16032798.service.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FoodController {

    //FoodServiceImpl foodService = new FoodServiceImpl();
    @Autowired FoodServiceImpl foodService = new FoodServiceImpl();

    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public ResponseEntity<List<Food>> welcome() {
        Iterable<Food> foodItems = foodService.findAllFood();
        List<Food> returnFoodItems = new ArrayList<>();

        foodItems.forEach(returnFoodItems::add);

        return new ResponseEntity<List<Food>>(returnFoodItems, HttpStatus.OK);
    }

    @RequestMapping(value = "/food/{name}", method = RequestMethod.GET)
    public ResponseEntity<Food> getFoodByName(@PathVariable String name) {
        return new ResponseEntity<>(foodService.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/food/add", method = RequestMethod.POST)
    public ResponseEntity<String> addFood(@RequestBody Map<String, Object> payload/*@RequestParam String name, @RequestParam int servingCount, @RequestParam ArrayList<String> ingredients, @RequestParam double price*/) {
        Food food = new Food(payload);
        foodService.saveFood(food);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
