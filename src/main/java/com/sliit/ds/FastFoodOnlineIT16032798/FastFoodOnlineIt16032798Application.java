package com.sliit.ds.FastFoodOnlineIT16032798;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Food;
import com.sliit.ds.FastFoodOnlineIT16032798.model.User;
import com.sliit.ds.FastFoodOnlineIT16032798.repository.FoodRepository;
import com.sliit.ds.FastFoodOnlineIT16032798.repository.UserRepository;
import com.sliit.ds.FastFoodOnlineIT16032798.service.FoodService;
import com.sliit.ds.FastFoodOnlineIT16032798.service.FoodServiceImpl;
import com.sliit.ds.FastFoodOnlineIT16032798.service.UserService;
import com.sliit.ds.FastFoodOnlineIT16032798.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class FastFoodOnlineIt16032798Application implements CommandLineRunner{

    @Autowired
	private FoodRepository foodRepository;

    @Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(FastFoodOnlineIt16032798Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        // we are adding a few food items and a user so that the demo is functional with data.
        Food chickenFriedRice = new Food();
        ArrayList<String> ingredients = new ArrayList<>();

        ingredients.add("Rice");
        ingredients.add("Chicken Pieces");
        ingredients.add("Carrot & Leaks");
        ingredients.add("Chili Paste");
        chickenFriedRice.setName("Chicken Fried Rice");
        chickenFriedRice.setPrice(250);
        chickenFriedRice.setServingCount(1);
        chickenFriedRice.setIngredients(ingredients);


        Food chickenBiriyani = new Food();

        ingredients.add("Roasted Chicken");
        chickenBiriyani.setName("Chicken Biriyani");
        chickenBiriyani.setIngredients(ingredients);
        chickenBiriyani.setServingCount(1);
        chickenBiriyani.setPrice(300);

        // save the 2 food items.
        foodRepository.save(chickenFriedRice);
        foodRepository.save(chickenBiriyani);


        // add an admin account.
        User admin = new User();
        admin.setPassword(92668751);    // hash code of "admin" as a password. Type "admin" for password when logging in.
        admin.setMobileNumber(0);
        admin.setAddress("Server");
        admin.setUid(1);
        admin.setName("Admin");
        admin.setEmail("admin@ffo.com");

        // save the admin account.
        userRepository.save(admin);
    }
}
