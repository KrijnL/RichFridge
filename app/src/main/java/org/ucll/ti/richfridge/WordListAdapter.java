package org.ucll.ti.richfridge;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private Button deleteButton;



        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.ingredient_text);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

    private WordViewModel model;
    private final LayoutInflater mInflater;
    private List<Word> mWords = Collections.emptyList(); // Cached copy of words
    private MyFridgeFragment fragment;
    private final static int FADE_DURATION = 1000;
    private int lastPosition = -1;


    WordListAdapter(Context context, WordViewModel viewModel ,MyFridgeFragment fragment) {
        mInflater = LayoutInflater.from(context);
        this.model = viewModel;
        this.fragment = fragment;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_ingredient, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word current = mWords.get(position);
        holder.wordItemView.setText(current.getWord());
        holder.deleteButton.setOnClickListener(new DeleteIngredientListener(current, model, fragment, position, this));
    }

    void setWords(List<Word> words, int pos) {
        mWords = words;
        notifyItemInserted(pos);
        //notifyDataSetChanged();
    }

    void setWords(List<Word> words) {
        mWords = words;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }


}


