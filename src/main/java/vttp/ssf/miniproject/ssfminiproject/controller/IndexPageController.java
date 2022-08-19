package vttp.ssf.miniproject.ssfminiproject.controller;

import java.util.ArrayList;

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

    @GetMapping("/")
    public String getHome(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "index";
    }

    private void recipeList(Model model) {
        Recipe r = new Recipe();
        ArrayList<Recipe> lsOfRecipes = recipeSvc.getRecipes(r);
        model.addAttribute("recipeList", lsOfRecipes);
    }

    @GetMapping("/recipes")
    public String showRecipes(@RequestParam(required = true) String ingredients, @RequestParam(required=true) int recipeNumber, Model model) {
        Recipe recipe = new Recipe();
        recipe.setIngredients(ingredients);
        recipe.setRecipeNumber(recipeNumber);
        logger.info("ingredients ---> " + recipe.getIngredients());
        logger.info("recipe number ---> " + recipe.getRecipeNumber());
        logger.info("recipe id >>>>>>>> " + recipe.getId());
        recipeList(model);
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", recipe.getIngredients());
        model.addAttribute("number", recipe.getRecipeNumber());
        model.addAttribute("recipeList", recipe); 
        return "showRecipes";
    }
    
}
