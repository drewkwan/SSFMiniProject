package vttp.ssf.miniproject.ssfminiproject.service;

import java.util.Optional;

import vttp.ssf.miniproject.ssfminiproject.model.User;

public interface UserRepoInterface {


    public Optional<User> findByUsername(final String username);


    
    public void save (final User user );




    
}
