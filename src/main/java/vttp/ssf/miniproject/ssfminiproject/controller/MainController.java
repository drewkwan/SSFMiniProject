package vttp.ssf.miniproject.ssfminiproject.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vttp.ssf.miniproject.ssfminiproject.model.Recipe;
import vttp.ssf.miniproject.ssfminiproject.model.RecipeInstructions;
import vttp.ssf.miniproject.ssfminiproject.model.UsedIngredients;
import vttp.ssf.miniproject.ssfminiproject.model.User;
import vttp.ssf.miniproject.ssfminiproject.service.RecipeService;
import vttp.ssf.miniproject.ssfminiproject.service.UserRedis;
import vttp.ssf.miniproject.ssfminiproject.service.UserServiceImpl;

@Controller
public class MainController {

    @Autowired
    RecipeService recipeSvc;

    @Autowired
    private UserRedis userRedisService;

    @Autowired
    UserServiceImpl userServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    //1) Get login page
    @GetMapping("/")
    public String home(Model model) {
        if (userServiceImpl.getCurrUserInSession() == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
        User user = userServiceImpl.getCurrUserInSession();
        model.addAttribute("user", user);
        }
        return "index";
    }

    //2) Verify login page
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        // logger.info("checking user >>>>>>" + userServiceImpl.getCurrUserInSession().getUsername());
        return "login";
    }


    @PostMapping("/login")
    public String submtiLogin(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
        String username = user.getUsername();
        // userRedisService.save(user);
        boolean isLoggedIn = userServiceImpl.login(username);
        model.addAttribute(user);
        if(!isLoggedIn) {
        redirectAttributes.addFlashAttribute("error", "Invalid username!");
        }
        
        return("redirect:");

    }

    @PostMapping("/logout")
    public String submitLogoutForm(@ModelAttribute ("user") User user, Model model) {
        String username = user.getUsername();
        boolean isUserInDb = userServiceImpl.logout(username);
        return("redirect:login");
    }

    @GetMapping("/register") 
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        String username = user.getUsername();
        boolean isCreated = userServiceImpl.create(username);
        if (isCreated) {
            redirectAttributes.addFlashAttribute("success!", "Your account is created successfully! You may log in now.");
        } else {
            redirectAttributes.addFlashAttribute("Error", "this username has been taken already. Please try again.");
        }
        return "redirect:";
    }

    //If login passes, redirect to search
    @GetMapping("/search")
    public String getSearch(@ModelAttribute("user") User user, Model model) {

        logger.info("Checking user still logged in >>>>" + userServiceImpl.getCurrUserInSession().getUsername());
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "searchRecipes";
    }

    private void recipeList(Model model, Recipe r) { 
        ArrayList<Recipe> lsOfRecipes = recipeSvc.getRecipes(r);
        model.addAttribute("recipeList", lsOfRecipes);
    }

    //Show the recipes available
    @GetMapping("/recipes")
    public String showRecipes(@ModelAttribute("user") User user, @RequestParam(required = true) String ingredients, @RequestParam(required=true) int recipeNumber, Model model, Recipe recipe) {
        
        String ingredientsSplit = ingredients.replace(" ", "");

        logger.info("Checking user still logged in >>>>" + userServiceImpl.getCurrUserInSession().getUsername());

        recipe.setIngredients(ingredientsSplit);
        recipe.setRecipeNumber(recipeNumber);
        logger.info("ingredients ---> " + recipe.getIngredients());
        logger.info("recipe number ---> " + recipe.getRecipeNumber());
        recipeList(model, recipe);
        model.addAttribute("user", user);
        return "showRecipes";
    }

    //Get the recipe instructions
    @PostMapping("/recipes/instructions")
    public String getRecipeById(@RequestParam Integer id, @RequestParam String recipeTitle,
    @RequestParam String recipeImageUrl, @ModelAttribute("user") User user, @ModelAttribute("recipe") Recipe recipe, Model model) {
    // public String getRecipeById(@ModelAttribute Recipe recipe, Model model) {        

        logger.info("IDX: " + id);
        logger.info("checking for user >>>>>>>>>> " + userServiceImpl.getCurrUserInSession().getUsername());
        recipe.setId((id));
        recipe.setTitle(recipeTitle);
        recipe.setImageUrl(recipeImageUrl);

        // recipe.setTitle(recipeTitle);
        logger.info(recipe.getTitle());
        stepsList(model, recipe); //adds the list of steps
        //can't seem to set the ingredients because list cannot be passed as parameter
        model.addAttribute("recipe", recipe); //adds the recipe with an Id and title
        return "recipeSteps";
    }

    public void stepsList(Model model, @ModelAttribute("recipe") Recipe recipe) {
        ArrayList<RecipeInstructions> lsOfRecipeInstructions = recipeSvc.getRecipeInstructions(recipe);
        model.addAttribute("lsOfRecipeInstructions", lsOfRecipeInstructions);
    }

    @GetMapping("/favourites")
    public String showFavourites(@ModelAttribute("user") User user, Model model) {
    
        logger.info("user favourite of " +userServiceImpl.getCurrUserInSession().getUsername() +" loading now............");
        User currUser = userServiceImpl.getCurrUserInSession();
        if ((currUser.getFavourites()) != null){
            model.addAttribute("lsOfFavourites", currUser.getFavourites());
        } else {
            logger.info("there is nothing to say");
            return "error";
        }
        return "favourites";
    }

    @PostMapping(path="/add") 
    public String addRecipe(@ModelAttribute("user") User user, @ModelAttribute Recipe recipe, @RequestParam Integer id, @RequestParam String recipeTitle,
    @RequestParam String recipeImageUrl, Model model) {
        recipe.setId(id);
        recipe.setTitle(recipeTitle);
        recipe.setImageUrl(recipeImageUrl);
        logger.info("adding recipe >>>>>>>>>>> " + recipe.getTitle());
        logger.info("adding favourites for >>> " + userServiceImpl.getCurrUserInSession().getUsername());
        User currUser = userServiceImpl.getCurrUserInSession();
        currUser.addToFavourites(recipe, currUser);
        userRedisService.save(currUser);

        model.addAttribute("user", currUser);
        model.addAttribute("lsOfFavourites", currUser.getFavourites());
        return "favourites";
    }

    @PostMapping(path="/delete")
    public String delRecipe(@ModelAttribute("user") User user, @ModelAttribute Recipe recipe, @RequestParam Integer id, @RequestParam String recipeTitle,
    @RequestParam String recipeImageUrl, Model model) {
        logger.info("user favourite of " +userServiceImpl.getCurrUserInSession().getUsername() +" loading now............");
        User currUser = userServiceImpl.getCurrUserInSession();
        if ((currUser.getFavourites()) != null){
            recipe.setId(id);
            recipe.setTitle(recipeTitle);
            recipe.setImageUrl(recipeImageUrl);
            logger.info("adding recipe >>>>>>>>>>> " + recipe.getTitle());
            logger.info("deleting favourite for >>> " + userServiceImpl.getCurrUserInSession().getUsername());
            currUser.delFromFavourites(recipe, currUser);
            userRedisService.save(currUser);
    
            model.addAttribute("user", currUser);
            model.addAttribute("lsOfFavourites", currUser.getFavourites());
            return "favourites";
        } else {
            logger.info("there is nothing to delete");
            return "favourites";
        }
    }


  
}

 
