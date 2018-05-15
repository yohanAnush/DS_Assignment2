package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import com.sliit.ds.FastFoodOnlineIT16032798.model.User;
import com.sliit.ds.FastFoodOnlineIT16032798.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService = new UserServiceImpl();

    // GET all users in the database.
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    // ADD a new user.
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody Map<String, Object> payload) {
        User user = new User(payload);
        userService.saveUser(user);
        // TODO ensure the email is not already in the database.

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
