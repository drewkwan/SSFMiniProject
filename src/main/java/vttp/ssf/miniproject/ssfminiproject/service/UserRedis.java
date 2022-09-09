package vttp.ssf.miniproject.ssfminiproject.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.User;

@Service
public class UserRedis implements UserRepo {

    private static final Logger logger = LoggerFactory.getLogger(UserRedis.class);

    @Autowired
    RedisTemplate<String, User> redisTemplate;

    // @Override
    // public void save(Recipe recipe) {
    //     logger.info("save Recipe > " + logger);
    //     redisTemplate.opsForValue().set(recipe.getId(), recipe);
    //     Recipe result = (Recipe) redisTemplate.opsForValue().get(recipe.getId());

    // }
        

    // @Override
    // public Recipe findById(int recipeId) {
    //     logger.info("find user by id >> " + recipeId);
    //     Recipe result = (Recipe) redisTemplate.opsForValue().get(recipeId);
    //     return result;
    // }


    @Override
    public void save(User user) {
        logger.info("saving user");
        redisTemplate.opsForValue().set(user.getUsername(), user);
        logger.info("save successful!");

        
    }


    @Override
    public Optional<User> findByUsername(String username) {
        // TODO Auto-generated method stub
        logger.info("find user by username>  " + username);
        try{
        User result = (User) redisTemplate.opsForValue().get(username);
        return Optional.of(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }


    // @Override
    // public int update(User user) {
    //     // TODO Auto-generated method stub
    //     return 0;
    // }

    
    
}
