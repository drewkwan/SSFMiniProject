package vttp.ssf.miniproject.ssfminiproject.service;

import java.util.Optional;

import vttp.ssf.miniproject.ssfminiproject.model.User;

public interface UserRepo {

    public void save(final User user);

    public Optional<User> findByUsername(final String username);

    // public int update(final User user);



    
}
