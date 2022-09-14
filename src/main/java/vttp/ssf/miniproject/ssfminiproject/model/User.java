package vttp.ssf.miniproject.ssfminiproject.model;

import java.util.ArrayList;


public class User {

    private String username;
    private boolean isLoggedIn;

   

    private ArrayList<Recipe> favourites= new ArrayList<>(); 
    
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public ArrayList<Recipe> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Recipe> favourites) {
        this.favourites = favourites;
    }

    public User() {
        this.isLoggedIn = false;
    }

    public User(String username) {
        this.username = username;
        this.isLoggedIn = false;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void delFromFavourites() {
        
    }

    public void addToFavourites() {
    }
    

    @Override
    public String toString() {
        return this.username;
    }

}
