package vttp.ssf.miniproject.ssfminiproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.ssf.miniproject.ssfminiproject.Repository.UserDaoImpl;
import vttp.ssf.miniproject.ssfminiproject.model.User;

import java.util.Map;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserDaoImpl userDaoImpl;

    // private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private User currUserInSession;

    @Override
    public boolean create(String username) {
        Integer newUserId = userDaoImpl.getNewUserId();
        User newUser = new User(username, newUserId);
        return userDaoImpl.addUserToDb(newUser);
    }

    @Override
    public boolean login(String username) {
        User user = userDaoImpl.getUserInDb(username);
        currUserInSession = (user != null) ? user : null;
        return (user != null) ? userDaoImpl.login(user) : false;
    }

    @Override
    public boolean logout(String username) {
        User userToLogOut = this.currUserInSession;
        currUserInSession = null;
        return userDaoImpl.logout(userToLogOut);
    }

    @Override
    public Map<Integer, User> getAllUsers() {
        return userDaoImpl.getAllUsers();
    }

    public User getCurrUserInSession() {
        return currUserInSession;
    }

    public void setCurrUserInSession(User user) {
        this.currUserInSession = user;
    }
}
