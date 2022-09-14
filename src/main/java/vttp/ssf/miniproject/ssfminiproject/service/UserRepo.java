package vttp.ssf.miniproject.ssfminiproject.service;

import java.util.Optional;

import vttp.ssf.miniproject.ssfminiproject.model.User;

public interface UserRepo {

    public void createUser(final String username);

    public Optional<User> findByUsername(final String username);

    // public int update(final User user);



    
}
