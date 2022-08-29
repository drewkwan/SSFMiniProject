package vttp.ssf.miniproject.ssfminiproject.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.RecipeInstructions;

@Service
public class RecipeService {
    //I'm envisioning the architecture of the project to be similar to the SSF Assessment.
    //Get List of Recipes and store all the key value pairs within each recipe object. Display a list of recipes on the webpage and save the recipes you want to keep into 
    //(cont'd) your redis database. retrieve the recipes by the id and att post and update capabilities
    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    public static final String RECIPE_LS_URL = "https://api.spoonacular.com/recipes/findByIngredients"; //spoonacular api
    //public static final String RECIPE_INSTRUCTIONS_URL = "https://api.spoonacular.com/recipes/{id}/analyzedInstructions"
    //apiKey = input later;
    
    public ArrayList<Recipe> getRecipes(Recipe recipe) {
        String apiKey = "";
         String recipeListUrl = UriComponentsBuilder.fromUriString(RECIPE_LS_URL)
                             .queryParam("ingredients", recipe.getIngredients())
                             .queryParam("number", recipe.getRecipeNumber())
                             .queryParam("apiKey", apiKey)
                             .toUriString();
        //String recipeListUrl = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=apples,+flour,+sugar&number=2&apiKey=";

        logger.info("Recipe URI >>> " + recipeListUrl);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(recipeListUrl, String.class);
        // ArrayList<Recipe> lsOfRecipes = Recipe.lsOfRecipes(resp.getBody());
        // ResponseEntity<String> resp = null;

        // HttpHeaders headers = new HttpHeaders();
        // headers.set("apiKey", apiKey);
        // HttpEntity request = new HttpEntity(headers);
        // resp = template.exchange(recipeListUrl,HttpMethod.GET, request, String.class);
        // logger.info(resp.getBody());
        ArrayList<Recipe> lsOfRecipes = Recipe.lsOfRecipes(resp.getBody());
        return lsOfRecipes;
    
    }

    public ArrayList<RecipeInstructions> getRecipeInstructions(Recipe recipe) {
        String apiKey = "";
        String RECIPE_INSTRUCTIONS_URI = "https://api.spoonacular.com/recipes/" + recipe.getId() + "/analyzedInstructions"; //do i need to query param this? --> Not required
        //String RECIPE_INSTRUCTIONS_URI = "https://api.spoonacular.com/recipes/11282/analyzedInstructions";
        System.out.println("test");
        logger.info("test ::::::::::: " + recipe.getId());
        String getRecipeUrl = UriComponentsBuilder.fromUriString(RECIPE_INSTRUCTIONS_URI).queryParam("apiKey", apiKey).toUriString();       
        logger.info("Recipe URI >>>>>>>> " + getRecipeUrl);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(getRecipeUrl, String.class);
        ArrayList<RecipeInstructions> recipeInstructions = RecipeInstructions.createLsOfInstructions(resp.getBody());
        return recipeInstructions;




    }
    
}
