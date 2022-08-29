package vttp.ssf.miniproject.ssfminiproject.service;

import vttp.ssf.miniproject.ssfminiproject.model.User;

public interface UserRepo {

    public int save(User user);

    public User findById(final String userId);

    
}
