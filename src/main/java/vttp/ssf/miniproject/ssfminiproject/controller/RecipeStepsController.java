package vttp.ssf.miniproject.ssfminiproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.RecipeInstructions;
import vttp.ssf.miniproject.ssfminiproject.service.RecipeService;

@Controller
public class RecipeStepsController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeStepsController.class);


    @Autowired
    RecipeService recipeSvc;

    @GetMapping(path="/recipes/{id}")
    public String getRecipeById(Model model, Recipe recipe) {
        System.out.println("Test");
        RecipeInstructions recipeInstructions = new RecipeInstructions();
        recipeSvc.getRecipeInstructions(recipe);
        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeInstructions", recipeInstructions);
        return "recipeSteps";
    }


}
