package vttp.ssf.miniproject.ssfminiproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.JsonObject;

public class UsedIngredients {
    
    private static final Logger logger = LoggerFactory.getLogger(UsedIngredients.class);
    
    private int id;
    private double amount;
    private String unit;
    private String unitLong;
    private String unitShort;
    private String aisle;
    private String name;
    private String original;
    private String originalName;
    private String imageUrl; //key-value pair "image"


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnitLong() {
        return unitLong;
    }
    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }
    public String getUnitShort() {
        return unitShort;
    }
    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }
    public String getAisle() {
        return aisle;
    }
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOriginal() {
        return original;
    }
    public void setOriginal(String original) {
        this.original = original;
    }
    public String getOriginalName() {
        return originalName;
    }
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
    // public String getMeta() {
    //     return meta;
    // }
    // public void setMeta(String meta) {
    //     this.meta = meta;
    // } this one i a bit lazy
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public static UsedIngredients createUsedIngredients(JsonObject o) {
        UsedIngredients usedIngredients = new UsedIngredients();
        //String jsonId = o.getString("id");
        usedIngredients.setId(o.getInt("id"));
        usedIngredients.setAmount(o.getJsonNumber("amount").doubleValue());
        usedIngredients.setUnit(o.getString("unit"));
        usedIngredients.setUnitLong((o.getString("unitLong")));
        usedIngredients.setUnitShort((o.getString("unitShort")));
        usedIngredients.setAisle(o.getString("aisle"));
        usedIngredients.setName(o.getString("name"));
        usedIngredients.setOriginal((o.getString("original")));
        usedIngredients.setOriginalName(o.getString("originalName"));
        usedIngredients.setImageUrl(o.getString("image"));


        return usedIngredients;
    }

}
