package org.ucll.ti.richfridge;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

public class SettingsViewModel extends AndroidViewModel {

    private boolean randomRecipe;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        this.randomRecipe = true;
    }

    public void setRandomRecipe(boolean val){ this.randomRecipe = val;}

    public boolean getRandomRecipe(){return this.randomRecipe;}


}
