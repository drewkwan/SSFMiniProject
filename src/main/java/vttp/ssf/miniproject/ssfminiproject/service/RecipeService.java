package vttp.ssf.miniproject.ssfminiproject.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;

@Service
public class RecipeService {
    //I'm envisioning the architecture of the project to be similar to the SSF Assessment.
    //Get List of Recipes and store all the key value pairs within each recipe object. Display a list of recipes on the webpage and save the recipes you want to keep into 
    //(cont'd) your redis database. retrieve the recipes by the id and att post and update capabilities
    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    public static final String RECIPE_LS_URL = "https://api.spoonacular.com/recipes/findByIngredients";
    //apiKey = input later;
    
    public ArrayList<Recipe> getRecipes() {
        return null;
    }
    
}
