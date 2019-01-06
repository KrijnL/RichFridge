package org.ucll.ti.richfridge;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

public class DetailViewModel extends AndroidViewModel {


    private Recipe recipe;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        recipe = new Recipe();
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
    }
}
