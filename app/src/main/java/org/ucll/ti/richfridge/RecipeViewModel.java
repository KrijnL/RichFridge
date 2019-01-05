package org.ucll.ti.richfridge;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {


    private RecipeDbInMemory mRecipeDb;
    private List<Recipe> mRecipes;


    public RecipeViewModel(@NonNull Application application) {
        super(application);
        mRecipeDb = new RecipeDbInMemory();
        mRecipes = mRecipeDb.getAll();
    }

    public List<Recipe> getRecipes() {return mRecipes;}
}
