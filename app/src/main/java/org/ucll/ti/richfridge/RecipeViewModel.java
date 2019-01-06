package org.ucll.ti.richfridge;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel extends AndroidViewModel {


    private RecipeDbInMemory mRecipeDb;
    private List<Recipe> mRecipes;
    private boolean searchFavorites;


    public RecipeViewModel(@NonNull Application application) {
        super(application);
        mRecipeDb = new RecipeDbInMemory();
        mRecipes = mRecipeDb.getAll();
    }

    public List<Recipe> getRecipes() {return mRecipes;}

    public List<Recipe> searchRecipes(List<String> ingredients) {
        List<Recipe> recipes = new ArrayList<>();

        if(!searchFavorites) {

            for (Recipe r : mRecipes) {
                for (String s : ingredients) {
                    for (String ingredient : r.getIngredients()) {
                        if (s.equals(ingredient) && !recipes.contains(r))
                            recipes.add(r);
                    }
                }
            }
            if (recipes == null) {
                Recipe r = new Recipe();

                recipes.add(r);
            }
        }else{
            recipes = getFavorites();
        }
        return recipes;
    }

    public List<Recipe> getFavorites(){
        List<Recipe> recipes = new ArrayList<>();

        for(Recipe r : mRecipes){
            if(r.isFavorite())
                recipes.add(r);
        }
        return recipes;
    }

    public void setFavorite(int id){
        mRecipeDb.setFavorite(id);
    }

    public void setSearchFavorites(boolean value){
        this.searchFavorites = value;
    }
}
