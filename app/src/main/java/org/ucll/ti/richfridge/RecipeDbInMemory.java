package org.ucll.ti.richfridge;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RecipeDbInMemory {

    private static final String TAG = "RecipeDbInMEmory";
    List<Recipe> recipes;

    public RecipeDbInMemory(){

        recipes = new ArrayList<Recipe>();

        Recipe pasta = new Recipe(0,"pasta", "a lovely pasta",
                "https://cdn-image.myrecipes.com/sites/default/files/styles/medium_2x/public/image/lighten-up-america/pasta-vodka-cream-sauce-ck-x.jpg");

        Recipe pizza = new Recipe( 1, "Pizza", "Classical italian dish",
                "https://www.philips.be/c-dam/b2c/kitchen-machine/landing-page/pizza-thumbnail.jpg");

        add(pasta);
        add(pizza);
    }

    public void add(Recipe recipe){

        //if no ID assigned, assign ID automatically
        if(recipe.getId()==-1) {

            if (recipes.size() != 0) {
                recipe.setId(recipes.get(recipes.size() - 1).getId() + 1);
            } else {
                recipe.setId(0);
            }
        }else{
            for(Recipe r : recipes){
                if(r.getId() == recipe.getId()){
                    Log.e(TAG, "can't add same ID twice");
                }
            }
        }
        recipes.add(recipe);
    }

    public void delete(Recipe recipe){
        recipes.remove(recipe);
    }

    public void update(Recipe recipe){
        for(Recipe r : recipes){
            if(r.getId() == recipe.getId()){
                delete(r);
                recipes.add(recipe.getId(),recipe);
            }
        }
    }

    public Recipe get(int id){
        for(Recipe r : recipes){
            if(r.getId() == id){
                return r;
            }
        }
        Log.e(TAG, "cannot find a recipe with that id");
        return null;
    }

    public List<Recipe> getAll() {return recipes;}
}
