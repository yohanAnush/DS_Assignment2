package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Food;
import com.sliit.ds.FastFoodOnlineIT16032798.service.FoodServiceImpl;
import com.sliit.ds.FastFoodOnlineIT16032798.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodServiceImpl foodService = new FoodServiceImpl();

    @Autowired
    SessionServiceImpl sessionService = new SessionServiceImpl();


    // GET all the food items in the database.

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Food>> getAllFoodWithAuth() {
        // here, we want to show available food items to anyone regardless they have logged into or not,
        // so we ignore the authKey and won't do any validations on it.
        return new ResponseEntity<>(foodService.findAllFood(), HttpStatus.OK);
    }

    // GET a food item buy its id.
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Food> getFoodById(@RequestHeader("Authentication") long authKey, @PathVariable("id") String id) {
        if (sessionService.authenticate(authKey)) {
            return new ResponseEntity<>(foodService.findById(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Food(), HttpStatus.UNAUTHORIZED);
        }

    }

    // FIND a food item by its name.
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Food>> getFoodByName(@RequestHeader("Authentication") long authKey, @PathVariable String name) {
        return new ResponseEntity<>(foodService.findFoodContainingName(name), HttpStatus.OK);
    }

    // ADD a new food item.
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addFood(@RequestHeader("Authentication") long authKey, @RequestBody Map<String, Object> payload/*@RequestParam String name, @RequestParam int servingCount, @RequestParam ArrayList<String> ingredients, @RequestParam double price*/) {
        Food food = new Food(payload);
        foodService.saveFood(food);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // UPDATE an existing food item.(Update everything but the name).
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updateFood(@RequestHeader("Authentication") long authKey, @RequestBody Map<String, Object> payload) {
        Food foodUpdate = new Food(payload);
        foodService.updateFood(foodUpdate);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
