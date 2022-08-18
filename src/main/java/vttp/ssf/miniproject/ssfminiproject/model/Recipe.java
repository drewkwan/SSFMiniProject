package vttp.ssf.miniproject.ssfminiproject.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Recipe {

    private static final Logger logger = LoggerFactory.getLogger(Recipe.class);
    //parameters
    private int id; //returns the id number. Will need to parse this as integer
    private String imageUrl; //key-value pair is just "image"
    private String imageType; //jpg, png etc.
    private int likes; //dunno if need tbh. maybe cna set a popularity rank for the display? 
    private int missedIngredientCount; 
    private MissedIngredients missedIngredients;//this returns a whole new set of stuff in itself. make a missed ingredients cpint?
    private String title; //presents the recipe name
    private String unusedIngredients; //also will return a json array of unused ingredients. so far it's empty, lets see if we even need it
    private int usedIngredientCount; 
    private UsedIngredients usedIngredients; //also returns a Json array. Consider making class of this
    
    //request from the form
    private String ingredients; //comma separated, need to split number of parameters
    //I feel like the only required parameter should be the ingredients, and then the number of recipes is optional, but defaulted to like 10(?)
    private int recipeNumber =10;; //number of recipes being returned. indicated as "number" in Json file
    
    
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

    public MissedIngredients getMissedIngredients() {
        return missedIngredients;
    }
    public void setMissedIngredients(MissedIngredients missedIngredients) {
        this.missedIngredients = missedIngredients;
    }
    public UsedIngredients getUsedIngredients() {
        return usedIngredients;
    }
    public void setUsedIngredients(UsedIngredients usedIngredients) {
        this.usedIngredients = usedIngredients;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageType() {
        return imageType;
    }
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public int getMissedIngredientCount() {
        return missedIngredientCount;
    }
    public void setMissedIngredientCount(int missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUnusedIngredients() {
        return unusedIngredients;
    }
    public void setUnusedIngredients(String unusedIngredients) {
        this.unusedIngredients = unusedIngredients;
    }
    public int getUsedIngredientCount() {
        return usedIngredientCount;
    }
    public void setUsedIngredientCount(int usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    
    public static ArrayList<Recipe> lsOfRecipes(String json) {
        //returns id:, title, image(url), imagetype, usedingrediencount, missedingredientcount, missedingredients[{stuff}]
        //JsonArray jsonDataArray = new JsonArray(json);          
        logger.info("create Json " + json);
        //JsonArray recipeArray = new JsonArray(json);
        
        Recipe recipe = new Recipe();
        InputStream is = new ByteArrayInputStream(json.getBytes());
        try(JsonReader r = Json.createReader(is)) {
            JsonArray dataArray = r.readArray();
            ArrayList<Recipe> recipeList =new ArrayList<>();
            for (JsonValue dataValue:dataArray) {
                JsonObject o = dataValue.asJsonObject();
                
                recipe.setTitle(o.getString("title"));
                recipe.setId(o.getInt("id"));
                recipe.setImageUrl(o.getString("image"));
                recipe.setImageType(o.getString("imageType"));
                recipe.setUsedIngredientCount(o.getInt("usedIngredientCount"));
                recipe.setLikes(o.getInt("likes"));;
                recipe.setMissedIngredientCount(o.getInt("missedIngredientCount"));
                
                JsonArray jsonArr = o.getJsonArray("missedIngredients");
                for (JsonValue value:jsonArr) {
                    JsonObject missedIngObject = value.asJsonObject();
                    MissedIngredients missedIngredients = MissedIngredients.createMissedIngredients(missedIngObject);
                    recipe.setMissedIngredients(missedIngredients);
                }

                JsonArray jsonUsedIngArr = o.getJsonArray("usedIngredients");
                for (JsonValue value:jsonUsedIngArr) {
                    JsonObject usedIngObject = value.asJsonObject();
                    UsedIngredients usedIngredients = UsedIngredients.createUsedIngredients(usedIngObject);
                    recipe.setUsedIngredients(usedIngredients);
                }


                recipeList.add(recipe);
                logger.info("the recipe list !!>>> " + recipeList.get(0).toString());
                logger.info(recipe.getTitle().toString()); //foresee problems

                logger.info(recipe.toString()); //this is class will not log right


            }

            return recipeList;

        }



    }

    //consdier: creating a usedIngredients class and maybe a missedIngredients class also ? Or maybe just set and then add it after?

    
}
