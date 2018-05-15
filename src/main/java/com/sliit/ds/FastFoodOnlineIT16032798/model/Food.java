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

    public Food() { }

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
        this.ingredients = getIngredientsList(payload.get("ingredients"));
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

    // we are dealing with a json array here.
    public ArrayList<String> getIngredientsList(Object ingredients) {
        ArrayList<String> ingredientsList = new ArrayList<>();
        String ingredientsStr = ingredients.toString();

        // ingredients string will be enclosed in [] brackets, and separated by a comma.
        // ex: [Rice, Carrot, Leaks, Chicken pieces, Chillie paste]
        //remove the square brackets.
        ingredientsStr = ingredientsStr.replace("[", "");
        ingredientsStr = ingredientsStr.replace("]", "");
        // separate the individual ingredients.
        String[] ingredientsArr = ingredientsStr.split(",");

        for(String ingredient: ingredientsArr) {

            // since we split by the comma, when the string looks like Chillie paste, Chicken there will be a,
            // space before Chicken as an example. Therefore we need to remove that just for the neatness.
            if (ingredient.startsWith(" ")) {
                ingredient = ingredient.replaceFirst(" ", "");  // do only once, otherwise intended spaces will get replaced as well.
            }
            ingredientsList.add(ingredient);
        }

        return ingredientsList;
    }
}
