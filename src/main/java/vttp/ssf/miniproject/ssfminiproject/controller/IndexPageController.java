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
        recipeList(model);
        model.addAttribute("recipe", recipe);
        return "index";
    }

    private void recipeList(Model model) {
        ArrayList<Recipe> lsOfRecipes = recipeSvc.getRecipes();
        model.addAttribute("recipeList", lsOfRecipes);
    }

    @GetMapping("/recipes")
    public String showRecipes(@RequestParam(required = true) String ingredients, @RequestParam(required=true) int recipeNumber, Model model) {
        Recipe recipe = new Recipe();
        recipe.setIngredients(ingredients);
        recipe.setRecipeNumber(recipeNumber);
        logger.info("ingredients ---> " + ingredients);
        logger.info("recipe number ---> " + recipeNumber);
        logger.info("recipe id >>>>>>>> " + recipe.getId());
        model.addAttribute("recipe", recipe);
        return "showRecipes";
    }
    
}
