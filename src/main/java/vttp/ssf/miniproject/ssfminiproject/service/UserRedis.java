package vttp.ssf.miniproject.ssfminiproject.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp.ssf.miniproject.ssfminiproject.model.User;

@Service
public class UserRedis implements UserRepo {

    private User currUser;

    private static final Logger logger = LoggerFactory.getLogger(UserRedis.class);

    @Autowired
    RedisTemplate<String, User> redisTemplate;



    @Override
    public void createUser(String username) {
        User user = new User(username);
        logger.info("creating user");
        redisTemplate.opsForValue().set(user.getUsername(), user);
        logger.info("created user successfully!");
   
    }

    @Override
    public Optional<User> findByUsername(String username) {
        logger.info("find user by username>  " + username);

        try{
        User result = (User) redisTemplate.opsForValue().get(username);//kills it
        logger.info(result.toString());
        logger.info("user found ?>>>>>>>>>>>> " + result );
        return Optional.of(result);
        
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public void login(String username) {

        Optional<User> optUser = findByUsername(username); 
        //Creates new user if empty
         if (optUser.isEmpty()) {
            createUser(username);
            //sets here
            this.currUser = (User) redisTemplate.opsForValue().get(username);//kills it too
            System.out.println(redisTemplate.opsForValue().get(username));
            logger.info("new user created");
            this.currUser.setLoggedIn(true);
         } else {
            this.currUser = (User) redisTemplate.opsForValue().get(username);
            logger.info("existing user logged in");
            this.currUser.setLoggedIn(true);
        }


    } 

    public void logout(String username) {

        Optional<User> optUser = findByUsername(username);
        if (optUser != null) {
            this.currUser = optUser.get();
            this.currUser.setLoggedIn(false);
        }
    }

    public User getCurrUser() {
            return currUser;
        }

    
    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }
    
}
