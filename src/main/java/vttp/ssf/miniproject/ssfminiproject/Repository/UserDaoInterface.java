package vttp.ssf.miniproject.ssfminiproject.Repository;

import java.util.Map;

import vttp.ssf.miniproject.ssfminiproject.model.User;


public interface UserDaoInterface {
    
    boolean addUserToDb(User user);

    Integer getNewUserId();

    Map<Integer, User> getAllUsers();

    User getUserInDb(String username);

    boolean login(User user);
    
    boolean logout(User user);

}
