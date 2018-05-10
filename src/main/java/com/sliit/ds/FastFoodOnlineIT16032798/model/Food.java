package com.sliit.ds.FastFoodOnlineIT16032798.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Map;

@Document(collection = "food")
public class Food {

    @Id
    private String fId;     // generated automatically.
    private String name;
    private int servingCount;   // shows how many people can be served with 1 portion; couldn't come up with a better name, LOL.
    private ArrayList<String> ingredients;
    private double price;

    public Food(String name, int servingCount, ArrayList<String> ingredients, double price) {
        this.fId = "FD" + Long.toString(name.hashCode());
        this.name = name;
        this.servingCount = servingCount;
        this.ingredients = ingredients;
        this.price = price;
    }

    public Food(Map<String, Object> payload) {
        this.name = payload.get("name").toString();
        this.fId = "FD" + Long.toString(this.name.hashCode());
        this.servingCount = Integer.parseInt(payload.get("servingCount").toString());
        this.price = Double.parseDouble(payload.get("price").toString());
    }

    public String getfId() {
        return fId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServingCount() {
        return servingCount;
    }

    public void setServingCount(int servingCount) {
        this.servingCount = servingCount;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
