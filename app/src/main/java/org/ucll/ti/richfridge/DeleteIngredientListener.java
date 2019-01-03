package org.ucll.ti.richfridge;

import android.content.Context;
import android.util.Log;
import android.view.View;

public class DeleteIngredientListener implements View.OnClickListener {

    private Word word;
    private WordViewModel model;
    private static final String TAG = "DeleteIngredientListener";

    public DeleteIngredientListener(Word word, WordViewModel context){
        this.word = word;
        this.model = context;
        Log.e(TAG, word.getWord());
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, word.getWord());
        model.delete(word);
    }
}
