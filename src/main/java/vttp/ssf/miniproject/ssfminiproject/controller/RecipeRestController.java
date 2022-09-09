package vttp.ssf.miniproject.ssfminiproject.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import vttp.ssf.miniproject.ssfminiproject.model.User;
import vttp.ssf.miniproject.ssfminiproject.service.UserRedis;

@RestController
@RequestMapping(path="/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeRestController {

    @Autowired
    UserRedis service;

    private static final Logger logger = LoggerFactory.getLogger(RecipeRestController.class);

    @PostMapping 
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Creating user" + user.getUsername());
        return ResponseEntity.ok(user);
    }

    @GetMapping(path="/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        logger.info("username:       " + username);
        Optional<User> optUser = service.findByUsername(username);

        if (optUser.isEmpty()) {
            JsonObject jsonError = Json.createObjectBuilder()
                                    .add("error", "User " + username + "not found")
                                    .build();
            return ResponseEntity.status(404).body(jsonError.toString());
        }

        return ResponseEntity.ok(optUser.get());
    }


    //Update function
    @PutMapping(path="/update") 
    public ResponseEntity<User> updateUser (@RequestBody User user, @PathVariable String username) {

        //update function
        return ResponseEntity.ok(user);
    }

}
