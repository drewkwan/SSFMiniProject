package vttp.ssf.miniproject.ssfminiproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vttp.ssf.miniproject.ssfminiproject.model.User;

public class UserRedis implements UserRepo {

    private static final Logger logger = LoggerFactory.getLogger(UserRedis.class);

    @Override
    public int save(User user) {
        //to-do
        return 0;
    }

    @Override
    public User findById(String userId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
