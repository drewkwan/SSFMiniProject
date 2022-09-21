package vttp.ssf.miniproject.ssfminiproject.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.RecipeInstructions;

@Service
public class RecipeService {

    @Value("${apiKey}")
    String apiKey; 

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    public static final String RECIPE_LS_URL = "https://api.spoonacular.com/recipes/findByIngredients"; 
    
    public ArrayList<Recipe> getRecipes(Recipe recipe) {

        String recipeListUrl = UriComponentsBuilder.fromUriString(RECIPE_LS_URL)
                             .queryParam("ignorePantry", recipe.getIgnorePantry())
                             .queryParam("ingredients", recipe.getIngredients())
                             .queryParam("number", recipe.getRecipeNumber())
                             .queryParam("apiKey", apiKey)
                             .toUriString();

        logger.info("Recipe URI >>> " + recipeListUrl);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(recipeListUrl, String.class);
        ArrayList<Recipe> lsOfRecipes = Recipe.lsOfRecipes(resp.getBody());
        return lsOfRecipes;
    
    }

    public ArrayList<RecipeInstructions> getRecipeInstructions(Recipe recipe) {
        String RECIPE_INSTRUCTIONS_URI = "https://api.spoonacular.com/recipes/" + recipe.getId() + "/analyzedInstructions";
        System.out.println("test");
        logger.info("test ::::::::::: " + recipe.getId());
        String getRecipeUrl = UriComponentsBuilder.fromUriString(RECIPE_INSTRUCTIONS_URI).queryParam("apiKey", apiKey).toUriString();       
        logger.info("Recipe URI >>>>>>>> " + getRecipeUrl);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(getRecipeUrl, String.class);
        ArrayList<RecipeInstructions> recipeInstructionsList = RecipeInstructions.createLsOfInstructions(resp.getBody());
        return recipeInstructionsList;

    }
    
}
