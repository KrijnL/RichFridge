package org.ucll.ti.richfridge;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class IngredientRepository {

    private IngredientDAO mIngredientDAO;
    private LiveData<List<Ingredient>> mAllIngredients;

    public IngredientRepository(Application application){
        IngredientRoomDatabase db = IngredientRoomDatabase.getDatabase(application);
        mIngredientDAO = db.ingredientDAO();
        mAllIngredients = mIngredientDAO.getAllIngredients();
    }

    LiveData<List<Ingredient>> getAllIngredients(){
        return mAllIngredients;
    }

    public void insert (Ingredient ingredient){
        new insertAsyncTask(mIngredientDAO).execute(ingredient);
    }

    private static class insertAsyncTask extends AsyncTask<Ingredient, Void, Void> {

        private IngredientDAO mAsyncTaskDAO;

        insertAsyncTask(IngredientDAO dao){
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(final Ingredient... ingredients) {
            mAsyncTaskDAO.insert(ingredients[0]);
            return null;
        }
    }
}
