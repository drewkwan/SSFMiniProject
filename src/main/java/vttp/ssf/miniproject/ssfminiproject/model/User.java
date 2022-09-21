package vttp.ssf.miniproject.ssfminiproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vttp.ssf.miniproject.ssfminiproject.service.UserServiceImpl;


public class User implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private String username;
    private boolean isLoggedIn;
    private List<Recipe> favourites= new ArrayList<>(); 
    private int userId;
    
    @Autowired
    UserServiceImpl userServiceImpl;

    public User(String username, int userId) {
        this.username = username;
        this.userId = userId;
        this.favourites = new ArrayList<>();
        this.isLoggedIn = false;
    }

    public User(String username, boolean isLoggedIn, int userId) {
        this.username = username;
        this.isLoggedIn = isLoggedIn;
        this.favourites = new ArrayList<>();
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public List<Recipe> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Recipe> favourites) {
        this.favourites = favourites;
    }

    public User() {
        this.isLoggedIn = false;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void delFromFavourites(Recipe recipe, User user) {

        if (user.getFavourites().size() == 0) {
            logger.info("no favourites to delete!");
            return;
        } else { 
            for (int i = 0; i< favourites.size(); i++) {
                if((getFavourites().get(i).getId() == (recipe.getId()))) {
                    logger.info("removing recipe now.......");
                    favourites.remove(getFavourites().get(i));
                }

            }

        }
        
    }


    public void addToFavourites(Recipe recipe, User user) {
        
        if (user.getFavourites().isEmpty())  {
            user.getFavourites().add(recipe);
            logger.info("recipe has been added successfully!");
        } else  {
            boolean isDuplicate = containsDuplicate(user.getFavourites(), recipe);
            if (!isDuplicate) {
                user.getFavourites().add(recipe);
                logger.info("recipe has been added successfully!");
            } else{
                logger.info("recipe is already in favourites!");
            }
        }
    }

    public boolean containsDuplicate(List<Recipe> recipes, Recipe toCheck) {
        for (Recipe r : recipes) {
            if (r.getId() == toCheck.getId()) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "Username: " + this.getUsername() + " with ID " + this.getUserId();
    }

}
