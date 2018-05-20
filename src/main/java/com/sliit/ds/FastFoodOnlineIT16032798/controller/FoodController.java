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
@CrossOrigin
public class FoodController {

    @Autowired
    FoodServiceImpl foodService = new FoodServiceImpl();

    @Autowired
    SessionServiceImpl sessionService = new SessionServiceImpl();


    // GET all the food items in the database.
    @CrossOrigin
    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public ResponseEntity<List<Food>> getAllFoodWithAuth(@RequestHeader("Authentication") long authKey) {
        // here, we want to show available food items to anyone regardless they have logged into or not,
        // so we ignore the authKey and won't do any validations on it.
        System.out.println(authKey);
        return new ResponseEntity<>(foodService.findAllFood(), HttpStatus.OK);
    }

    // GET a food item buy its id.
    @CrossOrigin
    @RequestMapping(value = "{authKey}/food/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Food> getFoodById(@PathVariable("authKey") long authKey, @PathVariable("id") String id) {
        if (sessionService.authenticate(authKey)) {
            return new ResponseEntity<>(foodService.findById(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Food(), HttpStatus.UNAUTHORIZED);
        }

    }

    // FIND a food item by its name.
    @CrossOrigin
    @RequestMapping(value = "{authKey}/food/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Food>> getFoodByName(@PathVariable String name) {
        return new ResponseEntity<>(foodService.findFoodContainingName(name), HttpStatus.OK);
    }

    // ADD a new food item.
    @CrossOrigin
    @RequestMapping(value = "/food", method = RequestMethod.POST)
    public ResponseEntity<String> addFood(@RequestBody Map<String, Object> payload/*@RequestParam String name, @RequestParam int servingCount, @RequestParam ArrayList<String> ingredients, @RequestParam double price*/) {
        Food food = new Food(payload);
        foodService.saveFood(food);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // UPDATE an existing food item.(Update everything but the name).
    @CrossOrigin
    @RequestMapping(value = "/food", method = RequestMethod.PUT)
    public ResponseEntity<String> updateFood(@RequestBody Map<String, Object> payload) {
        Food foodUpdate = new Food(payload);
        foodService.updateFood(foodUpdate);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
