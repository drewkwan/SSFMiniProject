package vttp.ssf.miniproject.ssfminiproject.model;

public class Query {
    //what is the request. consider combiing this with the recipe class?
    private String ingredients; //comma separated, need to split number of parameters
    private String number; //number of recipes being returned
    private boolean limitLicense;//Whether the recipes should have an open license that allows display with proper attribution. Check in the meaning
    private int ranking; //Whether to maximize used ingredients (1) or minimize missing ingredients (2) first.
    private boolean ignorePantry;	//Whether to ignore typical pantry items, such as water, salt, flour, etc.
    
}
