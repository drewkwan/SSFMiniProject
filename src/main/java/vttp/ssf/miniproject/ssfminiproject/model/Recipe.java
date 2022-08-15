package vttp.ssf.miniproject.ssfminiproject.model;

public class Recipe {
    //parameters
    private int id; //returns the id number. Will need to parse this as integer
    private String imageUrl; //key-value pair is just "image"
    private String imageType; //jpg, png etc.
    private int likes; //dunno if need tbh. maybe cna set a popularity rank for the display? 
    private int missedIngredientCount; 
    private int missedIngredients;//this returns a whole new set of stuff in itself. make a missed ingredients cpint?
    private String title; //presents the recipe name
    private String unusedIngredients; //also will return a json array of unused ingredients
    private int usedIngredientCount; 
    private String usedIngredients; //also returns a Json array. Consider making class of this
    
    
}
