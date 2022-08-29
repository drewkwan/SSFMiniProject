package vttp.ssf.miniproject.ssfminiproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.User;

public class UserRedis implements UserRepo {

    private static final Logger logger = LoggerFactory.getLogger(UserRedis.class);

    @Autowired
    RedisTemplate<Integer, Recipe> redisTemplate;

    @Override
    public void save(Recipe recipe) {
        logger.info("save Recipe > " + logger);
        redisTemplate.opsForValue().set(recipe.getId(), recipe);
        Recipe result = (Recipe) redisTemplate.opsForValue().get(recipe.getId());

    }
        

    @Override
    public Recipe findById(int recipeId) {
        logger.info("find user by id >> " + recipeId);
        Recipe result = (Recipe) redisTemplate.opsForValue().get(recipeId);
        return result;
    }

    
    
}
