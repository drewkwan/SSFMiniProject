package vttp.ssf.miniproject.ssfminiproject.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Recipe implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Recipe.class);

    private int id;
    private String imageUrl;
    private String imageType;
    private int likes;  
    private int missedIngredientCount; 
    private ArrayList<MissedIngredients> missedIngredientsList;
    private String title; 
    private String unusedIngredients; 
    private int usedIngredientCount; 
    private ArrayList<UsedIngredients> usedIngredientsList; 
    
    private String ingredients; 
    private int recipeNumber; 
    
    
    private boolean limitLicense;
    private int ranking;
    private String ignorePantry ="false";	

    
    public String getIgnorePantry() {
        return ignorePantry;
    }
    public void setIgnorePantry(String ignorePantry) {
        this.ignorePantry = ignorePantry;
    }

    public ArrayList<MissedIngredients> getMissedIngredientsList() {
        return missedIngredientsList;
    }
    public void setMissedIngredientsList(ArrayList<MissedIngredients> missedIngredientsList) {
        this.missedIngredientsList = missedIngredientsList;
    }
    public ArrayList<UsedIngredients> getUsedIngredientsList() {
        return usedIngredientsList;
    }
    public void setUsedIngredientsList(ArrayList<UsedIngredients> usedIngredientsList) {
        this.usedIngredientsList = usedIngredientsList;
    }
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
       
        InputStream is = new ByteArrayInputStream(json.getBytes());
        try(JsonReader r = Json.createReader(is)) {
            JsonArray dataArray = r.readArray();
            logger.info("Json data >>>>>>" + dataArray);
            ArrayList<Recipe> recipeList =new ArrayList<>();
            //add if condition(?)
            //if dataArray hasNext
            for (JsonValue dataValue:dataArray) {
                if (dataValue!=null) {
                    JsonObject o = dataValue.asJsonObject();
                    
                    System.out.println("recipe ID>>>>>>>>> " + o.getInt("id"));
                    //Instantiate the new recipe class inside the loop so that you create a new recipe everytime
                    Recipe recipe = new Recipe();
                    recipe.setTitle(o.getString("title"));
                    recipe.setId(o.getInt("id"));
                    recipe.setImageUrl(o.getString("image"));
                    recipe.setImageType(o.getString("imageType"));
                    recipe.setUsedIngredientCount(o.getInt("usedIngredientCount"));
                    recipe.setLikes(o.getInt("likes"));;
                    recipe.setMissedIngredientCount(o.getInt("missedIngredientCount"));
                    
                    JsonArray jsonMissedIngArr = o.getJsonArray("missedIngredients");
                    ArrayList<MissedIngredients> missedIngredientsList = new ArrayList<>();
                    for (JsonValue value:jsonMissedIngArr) {
                        JsonObject missedIngObject = value.asJsonObject();
                        MissedIngredients missedIngredients = MissedIngredients.createMissedIngredients(missedIngObject);
                        missedIngredientsList.add(missedIngredients);
                        recipe.setMissedIngredientsList(missedIngredientsList);
                    }

                    JsonArray jsonUsedIngArr = o.getJsonArray("usedIngredients");
                    ArrayList<UsedIngredients> usedIngredientsList = new ArrayList<>();
                    for (JsonValue value:jsonUsedIngArr) {
                        JsonObject usedIngObject = value.asJsonObject();
                        UsedIngredients usedIngredients = UsedIngredients.createUsedIngredients(usedIngObject);
                        usedIngredientsList.add(usedIngredients);
                        recipe.setUsedIngredientsList(usedIngredientsList);
                    }


                    recipeList.add(recipe);
                    logger.info("the recipe list !!>>> " + recipeList.size());
                    logger.info(recipe.getTitle().toString());
                } else {
                    return null;
                }
            }
            return recipeList;

        }



    }





    
}
