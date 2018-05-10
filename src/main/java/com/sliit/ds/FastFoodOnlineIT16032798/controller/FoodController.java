package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Food;
import com.sliit.ds.FastFoodOnlineIT16032798.repository.FoodRepository;
import com.sliit.ds.FastFoodOnlineIT16032798.service.FoodService;
import com.sliit.ds.FastFoodOnlineIT16032798.service.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FoodController {

    FoodServiceImpl foodService = new FoodServiceImpl();

    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public ResponseEntity<String> welcome() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/food/add", method = RequestMethod.POST)
    public ResponseEntity<String> addFood(@RequestBody Map<String, Object> payload/*@RequestParam String name, @RequestParam int servingCount, @RequestParam ArrayList<String> ingredients, @RequestParam double price*/) {
        Food food = new Food(payload);
        foodService.addFood(food);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
