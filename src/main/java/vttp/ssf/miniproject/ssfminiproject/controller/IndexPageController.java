package vttp.ssf.miniproject.ssfminiproject.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.service.RecipeService;

@Controller
public class IndexPageController {

    @Autowired
    RecipeService recipeSvc;

    private static final Logger logger = LoggerFactory.getLogger(IndexPageController.class);
    @GetMapping
    public String getHome(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "index";
    }

    @GetMapping("/search")
    public String getSearchDirect(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "searchRecipes";
    }
    //I should remove this once I'm able to process the login.

    @PostMapping("/search")
    public String getSearch(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "searchRecipes";
    }

    private void recipeList(Model model, Recipe r) {
        // Recipe r = new Recipe();
        //needed to add recipe r to the recipe list, because when i intialised a new Recipe() in the code above,
        //it basically meant that my recipelist was taking in an empty recipe rather than the one from the html file.  
        ArrayList<Recipe> lsOfRecipes = recipeSvc.getRecipes(r);
        //Recipe testRecipe = lsOfRecipes.get(0);
        //System.out.println("test recipeID >>>>>>>>>>> " + testRecipe.getId());
        model.addAttribute("recipeList", lsOfRecipes);
    }

    @GetMapping("/recipes")
    public String showRecipes(@RequestParam(required = true) String ingredients, @RequestParam(required=true) int recipeNumber, Model model, Recipe recipe) {
        String ingredientsSplit = ingredients.replace(" ", "");
        //current issue with the code above is that it can do numerous ingredients with space, but cannot process like two-worded ingredients
        //if i don't use the split, you have to input ingredients without the space. Seems like a dynamic form would work better.
        //also appears as though if there aren't enough recipes it will also throw error
        
        //Consider doing an Optional because right now when it errors it kills the page.
        recipe.setIngredients(ingredientsSplit);
        recipe.setRecipeNumber(recipeNumber);
        logger.info("ingredients ---> " + recipe.getIngredients());
        logger.info("recipe number ---> " + recipe.getRecipeNumber());
        recipeList(model, recipe);
        // model.addAttribute("recipe", recipe);


        //Hmm the bottom three were redundant
        // model.addAttribute("ingredients", recipe.getIngredients());
        // model.addAttribute("number", recipe.getRecipeNumber());
        // model.addAttribute("recipeList", recipe); 

        return "showRecipes";
        //return "showRecipes";
    }
    //if theres no more data it will throw error, but if there is empty data, no error is thrown. 

    // @PostMapping("/recipes")
    // public String savedRecipes(@ModelAttribute Model model) {

    //     return "showRecipes";

    // }
    
}
