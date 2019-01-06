package org.ucll.ti.richfridge;


import java.util.List;

public class Recipe {

    private int id;
    private String title;
    private String description;
    private String imageURL;
    private List<String> ingredients;
    private List<String> steps;
    private boolean isFavorite = false;

    public Recipe(){
        setId(-1);
        setTitle("Empty");
        setDescription("Empty Description");
        setImageURL("https://visualpharm.com/assets/781/Nothing%20Found-595b40b85ba036ed117dc2eb.svg");

    }

    public Recipe(String title, String description, String imageURL){
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        setId(-1);
    }

    public Recipe(String title, String description, String imageURL,List<String> ingredients, List<String> steps){
        this(title, description, imageURL);
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

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public boolean isFavorite(){
        return this.isFavorite;
    }

    public void setFavorite( boolean favorite){
        this.isFavorite = favorite;
    }
}
