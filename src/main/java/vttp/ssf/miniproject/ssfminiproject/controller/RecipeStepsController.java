package vttp.ssf.miniproject.ssfminiproject.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.RecipeInstructions;
import vttp.ssf.miniproject.ssfminiproject.model.Steps;
import vttp.ssf.miniproject.ssfminiproject.service.RecipeService;

@Controller
public class RecipeStepsController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeStepsController.class);


    @Autowired
    RecipeService recipeSvc;

    @GetMapping("/recipes/instructions" )
    public String getRecipeById(@RequestParam Integer idx, Model model) {
    // public String getRecipeById(@ModelAttribute Recipe recipe, Model model) {        
        System.out.println("IDX: " + idx);
        // System.out.println("BROBROBRO " + recipe.getId());
        Recipe recipe = new Recipe();
        System.out.println("another one )))))))))))) " + recipe.getId() );
        recipe.setId((idx));
        RecipeInstructions recipeInstructions = new RecipeInstructions();
        stepsList(model, recipe);
        recipeSvc.getRecipeInstructions(recipe);
        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeInstructions", recipeInstructions);
        return "recipeSteps";
    }

    public void stepsList(Model model, Recipe recipe) {
        ArrayList<RecipeInstructions> recipeSteps = recipeSvc.getRecipeInstructions(recipe);
        model.addAttribute("recipeSteps", recipeSteps);
    }

    //SO  when a getampping is used, it sends all the ids across. When post mapping is used, only the first one is sent.

}
