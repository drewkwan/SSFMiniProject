package vttp.ssf.miniproject.ssfminiproject.service;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.User;

public interface UserRepo {

    public void save(Recipe recipe);

    public Recipe findById(int recipeId);

    
}
