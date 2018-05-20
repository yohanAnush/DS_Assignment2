package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Session;
import com.sliit.ds.FastFoodOnlineIT16032798.model.User;
import com.sliit.ds.FastFoodOnlineIT16032798.service.SessionServiceImpl;
import com.sliit.ds.FastFoodOnlineIT16032798.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService = new UserServiceImpl();

    @Autowired
    private SessionServiceImpl sessionService = new SessionServiceImpl();

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

        return ResponseEntity.status((HttpStatus.CREATED)).build();
    }


    // UPDATE existing user.
    @RequestMapping(value = "/{id}",  method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") long uid, @RequestBody User userUpdate) {
        userService.updateUser(uid, userUpdate);

        return ResponseEntity.status((HttpStatus.OK)).build();
    }

    // CHANGE password.
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUserPassword(@RequestBody Map<String, Object> payload) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    // VALIDATE user.
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String,String>> login(@RequestBody Map<String, Object> payload) {
        String email = payload.get("email").toString();
        String password = payload.get("email").toString();

        HashMap<String, String> status = new HashMap<>();
        status.put("success", "true");
       return new ResponseEntity(status, HttpStatus.OK);
    }

    // AUTHENTICATION.
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Object>> issueAuthKey(@RequestBody Map<String, Object> payload) {

        String email = payload.get("email").toString();
        String password = payload.get("password").toString();
        boolean validUser = userService.isPasswordCorrect(email, password);  // will return false if the user is not there anyways.
        Map<String, Object> response = new HashMap<>();

        // if the user is there and the password is correct,
        if (validUser) {
            long authKey = email.hashCode()*12;
            //if (authKey < 0) { authKey = authKey * -1; }
            // we need to store this key in the database for authenticating each proceeding request.
            Session session = new Session();
            session.setUid(userService.getUidOfEmail(email));
            session.setAuthKeyOfUid(authKey);
            sessionService.saveSession(session);

            response.put("success", true);
            response.put("data", session);

            return new ResponseEntity(response, HttpStatus.OK);
        }
        else {
            response.put("success", false);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/invoke", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> invokeAuthentication(@RequestHeader("Authentication") long authKey, @RequestHeader("ClientId") long uid) {
        sessionService.invokeSessionOfUser(uid);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
