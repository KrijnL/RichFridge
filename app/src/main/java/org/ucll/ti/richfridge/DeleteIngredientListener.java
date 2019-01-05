package org.ucll.ti.richfridge;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.List;

public class DeleteIngredientListener implements View.OnClickListener {

    private Word word;
    private WordViewModel model;
    private MyFridgeFragment fragment;
    private static final String TAG = "DeleteIngredientListener";
    private int pos;
    private WordListAdapter adapter;

    public DeleteIngredientListener(Word word, WordViewModel context, MyFridgeFragment fragment, int pos, WordListAdapter adapter){
        this.word = word;
        this.model = context;
        this.fragment = fragment;
        this.pos = pos;
        this.adapter = adapter;
        //Log.e(TAG, word.getWord());
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, word.getWord());
        model.delete(word);
        List<String> ingredients = fragment.getIngredients();
        boolean contains = false;
        for(String s : ingredients){
            if(s.equals(word.getWord()))
                contains = true;
        }
        if(fragment.isIngredient(word.getWord()) && !contains) {
            Log.e(TAG, word.getWord() + "isword!");
            ingredients.add(word.getWord());
        }
        fragment.fillSpinnerWithIngredients(ingredients);
        adapter.notifyItemRemoved(pos);

    }
}
