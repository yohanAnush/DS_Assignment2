package com.sliit.ds.FastFoodOnlineIT16032798.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/*
 * This class is responsible for issuing the authentication code for a client who just logged in.
 * The client is supposed to use that authentication code from there on, for all the requests that it sends.
 */

@Controller
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping(value = "?clientid={cid}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, String>> provideAuthKey(@PathVariable("cid") long cid) {
        // cid is basically the uid of the user.
        // we need to first see if a user exi
        long authKey = Long.toString(cid).hashCode();
        HashMap<String, String> response = new HashMap<>();
        response.put("key", Long.toString(authKey));

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
