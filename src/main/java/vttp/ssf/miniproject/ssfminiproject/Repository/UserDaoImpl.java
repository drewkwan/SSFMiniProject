package vttp.ssf.miniproject.ssfminiproject.Repository;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import vttp.ssf.miniproject.ssfminiproject.model.User;

@Repository
public class UserDaoImpl implements UserDaoInterface{


    private static final String KEY = "USER";

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Resource(name="redisTemplate") 
    private HashOperations<String, Integer, User> hashOperations;


    @Override
    public boolean addUserToDb(User user) {
        User userInDb = getUserInDb(user.getUsername());
        if(userInDb == null) {
            hashOperations.putIfAbsent(KEY, user.getUserId(), user);
            logger.info(user.toString() + "has been added into the DB successfully");
            return true;
        } else {
            logger.info(user.getUsername() + " already exists in the DB");
            return false;
        }
    }

    @Override
    public Integer getNewUserId() {
        Map<Integer, User> users = getAllUsers();
        return users.size() + 1;
    }

    @Override
    public Map<Integer, User> getAllUsers() {
        return hashOperations.entries(KEY);
    }

    @Override
    public User getUserInDb(String username) {
        Map<Integer, User> users = getAllUsers();

        for (Map.Entry<Integer, User> user : users.entrySet()) {
            User currUser = (User) user.getValue();
            if (currUser.getUsername().equals(username)) {
                return currUser;
            }
        }
        return null;
    }
        

    @Override
    public boolean login(User user) {
        user.setLoggedIn(true);
        hashOperations.put(KEY, user.getUserId(), user);
        logger.info(user.toString() + " has logged in successfully.");
        return true;
    }

    @Override
    public boolean logout(User user) {
        user.setLoggedIn(false);
        hashOperations.put(KEY, user.getUserId(), user);
        logger.info(user.toString() + " has logged out successfully");
        return false;
    }
    
}
