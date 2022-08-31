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

import ch.qos.logback.core.net.SyslogOutputStream;
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
    public String getRecipeById(@RequestParam Integer id, Model model) {
    // public String getRecipeById(@ModelAttribute Recipe recipe, Model model) {        
        System.out.println("IDX: " + id);
        // System.out.println("BROBROBRO " + recipe.getId());
        Recipe recipe = new Recipe();
        System.out.println("Id Test )))))))))))) " + recipe.getId() );
        recipe.setId((id));
        stepsList(model, recipe); //it prints here and then dies.
        // logger.info("stepNUMBER )))))))))) " + steps.getNumber()); 
        // logger.info("stepITSELF ))))))))))))) " +steps.getStep()); // Why are these two empty???
        //the two loggers are returning empty


        // model.addAttribute("recipeInstructions", recipeInstructions);
        // model.addAttribute("lsOfSteps", recipeInstructions.getLsOfSteps());
        // model.addAttribute("steps", steps);
        // model.addAttribute("stepNumber", steps.getNumber());
        // model.addAttribute("step", steps.getStep());
        return "recipeSteps";
    }

    public void stepsList(Model model, Recipe recipe) {
        ArrayList<RecipeInstructions> lsOfRecipeInstructions = recipeSvc.getRecipeInstructions(recipe);
        System.out.println("LSSOFFFRECIPINSTNURN89898998 .>>>>>>>>> " + lsOfRecipeInstructions.get(0).getLsOfSteps().get(0).getNumber());
        model.addAttribute("lsOfRecipeInstructions", lsOfRecipeInstructions);
    }

    //SO  when a getampping is used, it sends all the ids across. When post mapping is used, only the first one is sent.
    //I think with the loop rn im missing the create
}
