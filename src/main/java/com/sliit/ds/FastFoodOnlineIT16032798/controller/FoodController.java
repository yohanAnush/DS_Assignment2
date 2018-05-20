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
@CrossOrigin
@RequestMapping("/food")
public class FoodController {

    @Autowired FoodServiceImpl foodService = new FoodServiceImpl();


    // GET all the food items in the database.
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Food>> getAllFood() {
        Iterable<Food> foodItems = foodService.findAllFood();
        List<Food> returnFoodItems = new ArrayList<>();

        // add food items in the iterable to a list using a lambda.
        foodItems.forEach(returnFoodItems::add);

        return new ResponseEntity<>(returnFoodItems, HttpStatus.OK);
    }

    // GET a food item buy its id.
    @CrossOrigin
    @RequestMapping(value = "auth/{authKey}/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Food> getFoodById(@PathVariable("id") String id, @PathVariable("authKey") long authKey) {
        System.out.println(authKey);
        return new ResponseEntity<>(foodService.findById(id), HttpStatus.OK);
    }

    // FIND a food item by its name.
    @CrossOrigin
    @RequestMapping(value = "name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Food>> getFoodByName(@PathVariable String name) {
        return new ResponseEntity<>(foodService.findFoodContainingName(name), HttpStatus.OK);
    }

    // ADD a new food item.
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addFood(@RequestBody Map<String, Object> payload/*@RequestParam String name, @RequestParam int servingCount, @RequestParam ArrayList<String> ingredients, @RequestParam double price*/) {
        Food food = new Food(payload);
        foodService.saveFood(food);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // UPDATE an existing food item.(Update everything but the name).
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updateFood(@RequestBody Map<String, Object> payload) {
        Food foodUpdate = new Food(payload);
        foodService.updateFood(foodUpdate);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
