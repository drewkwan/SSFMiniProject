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
import vttp.ssf.miniproject.ssfminiproject.model.RecipeInstructions;
import vttp.ssf.miniproject.ssfminiproject.model.User;
import vttp.ssf.miniproject.ssfminiproject.service.RecipeService;

@Controller
public class IndexPageController {

    @Autowired
    RecipeService recipeSvc;

    private static final Logger logger = LoggerFactory.getLogger(IndexPageController.class);


    // @PostMapping("/login")
    // public String login(@RequestParam(required=true) String username, Model model) {
    //     User user = new User();

    // }

    @GetMapping("/")
    public String getLogin(Model model) {
        User user = new User();
        model.addAttribute("user", user);
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
    //public String getSearch(@RequestParam(value="username", require=true) String username, Model model) {
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

        //Bottom three were redundant
        // model.addAttribute("ingredients", recipe.getIngredients());
        // model.addAttribute("number", recipe.getRecipeNumber());
        // model.addAttribute("recipeList", recipe); 

        return "showRecipes";
        //return "showRecipes";
    }

    // @GetMapping("recipes/favourites")
    // public String showFavourites() {
        //the favourites portion should do a redis ,get value 
        //user.getLsOfRecipes()
        //model addAttrivbute(lsOfRecipes, lsofRecipes)
        //return showRecipes;
        //so the favourites page should return showRecipes since essentially its the same thing except this time instead of calling the api 
        //... we're retrieving from our own database
    // }

  
}

 
