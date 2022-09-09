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
        // System.out.println("Id Test )))))))))))) " + recipe.getId() );
        recipe.setId((id));
        recipe.setTitle(recipeTitle);
        stepsList(model, recipe); //it prints here and then dies.
        // logger.info("stepNUMBER )))))))))) " + steps.getNumber()); 
        // logger.info("stepITSELF ))))))))))))) " +steps.getStep()); // Why are these two empty???
        //the two loggers are returning empty


        // model.addAttribute("recipeInstructions", recipeInstructions);
        // model.addAttribute("lsOfSteps", recipeInstructions.getLsOfSteps());
        // model.addAttribute("steps", steps);
        // model.addAttribute("stepNumber", steps.getNumber());
        // model.addAttribute("step", steps.getStep());
        model.addAttribute("recipe", recipe);
        return "recipeStepsDraft";
    }

    public void stepsList(Model model, Recipe recipe) {
        ArrayList<RecipeInstructions> lsOfRecipeInstructions = recipeSvc.getRecipeInstructions(recipe);
        model.addAttribute("lsOfRecipeInstructions", lsOfRecipeInstructions);
    }

}
