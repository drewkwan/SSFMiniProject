package vttp.ssf.miniproject.ssfminiproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.JsonObject;

public class Steps {
    private static final Logger logger = LoggerFactory.getLogger(Steps.class);
    private int number;
    private String step;
    private String ingredients;

    
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getStep() {
        return step;
    }
    public void setStep(String step) {
        this.step = step;
    }
    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public static Steps createSteps(JsonObject stepsObj) {
        Steps steps = new Steps();
        steps.setNumber(stepsObj.getInt("number"));
        steps.setStep(stepsObj.getString("step"));
        logger.info("stepNumber -------- " +steps.getNumber());
        logger.info("step ---------> " + steps.getStep());
        
        return steps;
    }
    
}
