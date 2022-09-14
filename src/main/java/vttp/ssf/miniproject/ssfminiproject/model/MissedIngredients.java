package vttp.ssf.miniproject.ssfminiproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.JsonObject;

public class MissedIngredients {

    private static final Logger logger = LoggerFactory.getLogger(MissedIngredients.class);

    private int id;
    private double amount;
    private String unit;
    private String unitLong;
    private String unitShort;
    private String aisle;
    private String name;
    private String original;
    private String originalName;
    //private String meta;//within an array
    private String extendedName; 
    private String imageUrl; //seen as "image" key-value pair


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
    // }
    public String getExtendedName() {
        return extendedName;
    }
    public void setExtendedName(String extendedName) {
        this.extendedName = extendedName;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public static MissedIngredients createMissedIngredients(JsonObject o) {
        
        MissedIngredients missedIngredients = new MissedIngredients();
        missedIngredients.setId(o.getInt("id"));
        missedIngredients.setAmount(o.getJsonNumber("amount").doubleValue());
        missedIngredients.setUnit(o.getString("unit"));
        missedIngredients.setAisle(o.getString("aisle"));
        missedIngredients.setUnitLong(o.getString("unitLong"));
        missedIngredients.setUnitShort(o.getString("unitShort"));
        missedIngredients.setName(o.getString("name"));
        missedIngredients.setOriginal(o.getString("original"));
        missedIngredients.setOriginalName(o.getString("originalName"));
        missedIngredients.setImageUrl(o.getString("image"));


        return missedIngredients;
    }
}
