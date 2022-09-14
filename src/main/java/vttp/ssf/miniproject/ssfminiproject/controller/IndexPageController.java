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
import org.springframework.web.bind.annotation.RequestParam;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.User;
import vttp.ssf.miniproject.ssfminiproject.service.RecipeService;
import vttp.ssf.miniproject.ssfminiproject.service.UserRedis;

@Controller
public class IndexPageController {

    @Autowired
    RecipeService recipeSvc;

    @Autowired
    private UserRedis userService;

    private static final Logger logger = LoggerFactory.getLogger(IndexPageController.class);



    @GetMapping("/")
    public String getLogin(Model model) {
        return "index";
    }

    @PostMapping("/login")
    public String verifyLogin(@RequestParam(required=true) String username, Model model) {
        userService.login(username);
        //dies here
        model.addAttribute("currUser", username);
        return("redirect:/search");

    }

    // @GetMapping("/search")
    // public String getSearchDirect(Model model) {
    //     Recipe recipe = new Recipe();
    //     model.addAttribute("recipe", recipe);
    //     return "searchRecipes";
    // }
    //I should remove this once I'm able to process the login.

    @GetMapping("/search")
    // public String getSearch(@RequestParam(required=true) String username, Model model) {
    public String getSearch(Model model) {

        //Instantiate objects for the model
        Recipe recipe = new Recipe();
        // redisService.save(user);
        // model.addAttribute("user", user);
        model.addAttribute("recipe", recipe);
        return "searchRecipes";
    }

    private void recipeList(Model model, Recipe r) { 
        ArrayList<Recipe> lsOfRecipes = recipeSvc.getRecipes(r);
        model.addAttribute("recipeList", lsOfRecipes);
    }


    @GetMapping("/recipes")
    public String showRecipes(@ModelAttribute("user") User user, @RequestParam(required = true) String ingredients, @RequestParam(required=true) int recipeNumber, Model model, Recipe recipe) {
        String ingredientsSplit = ingredients.replace(" ", "");


        //Consider doing an Optional because right now when it errors it kills the page.
        recipe.setIngredients(ingredientsSplit);
        recipe.setRecipeNumber(recipeNumber);
        logger.info("ingredients ---> " + recipe.getIngredients());
        logger.info("recipe number ---> " + recipe.getRecipeNumber());
        // logger.info(user.getUsername().toString()); //is null, not sending
        recipeList(model, recipe);
        model.addAttribute("user", user);
        return "showRecipes";
    }

    // @GetMapping("recipes/favourites")
    // public String showFavourites(@ModelAttribute("currentUser") User user, Model model) {
        //the favourites portion should do a redis ,get value 
        //user.getLsOfRecipes()
        //model addAttrivbute(lsOfRecipes, lsofRecipes)
        //return showRecipes;
        //so the favourites page should return showRecipes since essentially its the same thing except this time instead of calling the api 
        //... we're retrieving from our own database
    // }

  
}

 
