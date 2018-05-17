package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import com.sliit.ds.FastFoodOnlineIT16032798.model.User;
import com.sliit.ds.FastFoodOnlineIT16032798.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService = new UserServiceImpl();

    // GET all users in the database.
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    // GET specific user by its uid, email or name.
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUid(@RequestParam("id") long uid) {
        return new ResponseEntity<>(userService.findByUid(uid), HttpStatus.OK);
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(userService.findUsersHavingName(name), HttpStatus.OK);
    }


    // ADD a new user.
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user) {

        user.setUid((user.getEmail()+user.getName()).hashCode());
        userService.saveUser(user);
        // TODO ensure the email is not already in the database.

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    // UPDATE existing user.
    @RequestMapping(value = "/{id}",  method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") long uid, @RequestBody User userUpdate) {

        userService.updateUser(uid, userUpdate);

       return ResponseEntity.status((HttpStatus.OK)).build();
    }
}
