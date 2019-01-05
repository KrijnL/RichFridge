package org.ucll.ti.richfridge;


import java.util.List;

public class Recipe {

    private int id;
    private String title;
    private String description;
    private String imageURL;
    private List<String> ingredients;
    private List<String> steps;

    public Recipe(String title, String description, String imageURL){
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        setId(-1);
    }

    public Recipe(String title, String description, String imageURL,List<String> ingredients, List<String> steps){
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.ingredients = ingredients;
        this.steps = steps;
        setId(-1);
    }

    public Recipe(int id, String title, String description, String imageURL){
        this(title, description, imageURL);
        setId(id);
    }


    public void setId(int id){
        this.id = id;
    }

    public int getId(){return id;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public String getImageURL(){return imageURL;}
    public void setIngredients(List<String> ingredients){this.ingredients = ingredients;}
    public List<String> getIngredients(){ return ingredients;}
    public void setSteps(List<String> steps){this.steps = steps;}
    public List<String> getSteps(){ return steps;}

    public void addIngredient(String ingredient){ingredients.add(ingredient);}
    public void deleteIngredient(String ingredient){ingredients.remove(ingredient);}

}
