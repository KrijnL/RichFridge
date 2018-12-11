package org.ucll.ti.richfridge;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {

    private IngredientRepository mRepository;
    private LiveData<List<Ingredient>> mAllIngredients;

    public IngredientViewModel(@NonNull Application application) {
        super(application);
        mRepository = new IngredientRepository(application);
        mAllIngredients = mRepository.getAllIngredients();
    }

    LiveData<List<Ingredient>> getAllIngredients(){
        return mAllIngredients;
    }

    public void insert(Ingredient ingredient){
        mRepository.insert(ingredient);
    }


}
