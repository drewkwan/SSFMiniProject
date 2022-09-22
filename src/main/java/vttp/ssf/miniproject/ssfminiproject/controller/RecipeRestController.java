package vttp.ssf.miniproject.ssfminiproject.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import vttp.ssf.miniproject.ssfminiproject.model.User;
import vttp.ssf.miniproject.ssfminiproject.service.UserRedisService;

@RestController
public class RecipeRestController {

    @Autowired
    UserRedisService redisService;

    private static final Logger logger = LoggerFactory.getLogger(RecipeRestController.class);


    @GetMapping(path="/users/{username}")
    public ResponseEntity<Optional<User>> getByUsername(@PathVariable String username) {
        logger.info("username:       " + username);
        Optional<User> optUser = redisService.findByUsername(username);
        logger.info("user loading.......... ");
        return ResponseEntity.ok(optUser); 
    }


}
