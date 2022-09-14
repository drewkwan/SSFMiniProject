package vttp.ssf.miniproject.ssfminiproject.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.RecipeInstructions;
import vttp.ssf.miniproject.ssfminiproject.service.RecipeService;

@Controller
public class RecipeStepsController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeStepsController.class);


    @Autowired
    RecipeService recipeSvc;

    @GetMapping("/recipes/instructions" )
    public String getRecipeById(@RequestParam Integer id, @RequestParam String recipeTitle, Model model ) {
    // public String getRecipeById(@ModelAttribute Recipe recipe, Model model) {        
        
        System.out.println("IDX: " + id);
        Recipe recipe = new Recipe();
        recipe.setId((id));
        recipe.setTitle(recipeTitle);
        stepsList(model, recipe); //adds the list of steps
        //can't seem to set the ingredients because list cannot be passed as parameter
        model.addAttribute("recipe", recipe); //adds the recipe with an Id and title
        return "recipeSteps";
    }

    public void stepsList(Model model, Recipe recipe) {
        ArrayList<RecipeInstructions> lsOfRecipeInstructions = recipeSvc.getRecipeInstructions(recipe);
        model.addAttribute("lsOfRecipeInstructions", lsOfRecipeInstructions);
    }

}
