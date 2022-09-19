package vttp.ssf.miniproject.ssfminiproject.service;

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
    RedisTemplate<String, Object> redisTemplate;


    @Override
    public Optional<User> findByUsername(String username) {
        logger.info("finding user by username....." + username);

        try{
            User result = (User) redisTemplate.opsForValue().get(username);//kills it
            logger.info("user found >>>>>>>>>>>> " + result );
            return Optional.of(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public User getCurrUser() {
            return currUser;
        }

    
    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    @Override
    public void save(User user) {
        logger.info("saving.........");
        redisTemplate.opsForValue().set(user.getUsername(),user);
        logger.info("saved successfully");
        
    }

    
}
