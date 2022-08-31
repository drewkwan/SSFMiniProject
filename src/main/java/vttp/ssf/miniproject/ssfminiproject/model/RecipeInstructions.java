package vttp.ssf.miniproject.ssfminiproject.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class RecipeInstructions {
    private static final Logger logger = LoggerFactory.getLogger(RecipeInstructions.class);
    private String name;
    private ArrayList<Steps> lsOfSteps;

    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Steps> getLsOfSteps() {
        return lsOfSteps;
    }
    public void setLsOfSteps(ArrayList<Steps> lsOfSteps) {
        this.lsOfSteps = lsOfSteps;
    }

    public static ArrayList<RecipeInstructions> createLsOfInstructions(String json) {
        logger.info("recipe steps Json >>>>>>>> " + json);

        InputStream is = new ByteArrayInputStream(json.getBytes());
        try (JsonReader r = Json.createReader(is)) {
            JsonArray instructionsArray = r.readArray();
            logger.info("Json stepsData>>>>>> " + instructionsArray);
            ArrayList<RecipeInstructions> lsOfRecipeInstructions = new ArrayList<>();
            for (JsonValue instructionsValue:instructionsArray) {
                JsonObject instructionsObject = instructionsValue.asJsonObject();
                RecipeInstructions recipeInstructions = new RecipeInstructions();
                recipeInstructions.setName(instructionsObject.getString("name"));
                logger.info("Name of instruction >>" + recipeInstructions.name);
                
                JsonArray jsonAllSteps = instructionsObject.getJsonArray("steps");
                ArrayList<Steps> lsOfSteps = new ArrayList<>();
                for (JsonValue stepsValue:jsonAllSteps) {
                    JsonObject stepsObj = stepsValue.asJsonObject();
                    Steps steps = Steps.createSteps(stepsObj); //create steps method sets the step number and step name
                    lsOfSteps.add(steps);
                    recipeInstructions.setLsOfSteps(lsOfSteps);
                }

                lsOfRecipeInstructions.add(recipeInstructions);
                logger.info("instructions List !!>>> " + lsOfRecipeInstructions.size());
                
                
            }

            return lsOfRecipeInstructions;

        }


    } 
}
