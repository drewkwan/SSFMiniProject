package vttp.ssf.miniproject.ssfminiproject.model;

import java.util.ArrayList;
import java.util.Random;

public class User {

    private String username;
    private String id;
    // private String password;
    private ArrayList<Recipe> favourites= new ArrayList<>();

    public ArrayList<Recipe> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Recipe> favourites) {
        this.favourites = favourites;
    }

    public User() {
        this.id = generateId(8);
    }

    public User(String username) {
        this.id = generateId(8);
        this.username = username;
    }

    public User(String username, String id) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    private synchronized String generateId(int numchars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }

    public void delFromFavourites() {
        
    }

    public void addToFavourites () {
        
    }

}
