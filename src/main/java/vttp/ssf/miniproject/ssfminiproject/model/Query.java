package vttp.ssf.miniproject.ssfminiproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Query {
    private static final Logger logger = LoggerFactory.getLogger(Query.class);
    //what is the request. consider combiing this with the recipe class?
    private String ingredients; //comma separated, need to split number of parameters
    //I feel like the only required parameter should be the ingredients, and then the number of recipes is optional, but defaulted to like 10(?)
    private int recipeNumber =10;; //number of recipes being returned. indicated as "numebr" in Json file
    
    
    private boolean limitLicense;//Whether the recipes should have an open license that allows display with proper attribution. Check in the meaning
    private int ranking; //Whether to maximize used ingredients (1) or minimize missing ingredients (2) first.
    private boolean ignorePantry;	//Whether to ignore typical pantry items, such as water, salt, flour, etc.
    //consider: creating an ignore pantry
    
    public int getRecipeNumber() {
        return recipeNumber;
    }
    public void setRecipeNumber(int recipeNumber) {
        this.recipeNumber = recipeNumber;
    }
    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    
    
    public boolean isLimitLicense() {
        return limitLicense;
    }
    public void setLimitLicense(boolean limitLicense) {
        this.limitLicense = limitLicense;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public boolean isIgnorePantry() {
        return ignorePantry;
    }
    public void setIgnorePantry(boolean ignorePantry) {
        this.ignorePantry = ignorePantry;
    }
    
}
