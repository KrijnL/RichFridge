package org.ucll.ti.richfridge;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MyFridgeFragment extends Fragment {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private WordViewModel mWordViewModel;
    private RecipeViewModel mRecipeViewModel;


    private WordListAdapter adapter;
    private IngredientList ingredientList;
    private List<String> ingredients;

    private Spinner spinner;
    private Button addIngredientButton;

    private Word newWord;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        // Get a new or existing ViewModel from the ViewModelProvider.
            mWordViewModel = ViewModelProviders.of(getActivity()).get(WordViewModel.class);

            mRecipeViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);

            adapter = new WordListAdapter(getContext(), mWordViewModel, this);

            ingredientList = new IngredientList();
            List<String> clone = ingredientList.getAll();
            ingredients = new ArrayList<>();

            for (String s : clone) {
                ingredients.add(s);
            }


            // Add an observer on the LiveData returned by getAlphabetizedWords.
            // The onChanged() method fires when the observed data changes and the activity is
            // in the foreground.
            mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                }
            });



        return inflater.inflate(R.layout.fragment_my_fridge, parent, false);




    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);

        navigationView.getMenu().getItem(0).setChecked(true);


        fillSpinnerWithIngredients();
        addListenerOnAddIngredientButton();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        Log.e("VIEW", recyclerView.toString());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int offsetPx = 50;

        recyclerView.addItemDecoration(new EndOffsetItemDecoration(offsetPx));
        getActivity().setTitle("Fridge");


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        Button findBtn = view.findViewById(R.id.button_find_recipes);
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecipeViewModel.setSearchFavorites(false);
                Fragment recipesFragment = new RecipesFragment();
                /*List<String> ingredients = new ArrayList<>();
                for(Word w : mWordViewModel.getAllWords().getValue()){
                    ingredients.add(w.getWord());
                }
                ((RecipesFragment) recipesFragment).setIngredients(ingredients);*/
                replaceFragmentWithAnimation(recipesFragment, "fridge");

                getActivity().setTitle("Recipes");
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY).toLowerCase());
            try {
                mWordViewModel.insert(word);
                newWord = word;
            } catch(Exception e){
                Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(
                    getContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public WordViewModel getmWordViewModel(){
        return mWordViewModel;
    }

    public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void fillSpinnerWithIngredients() {
        fillSpinnerWithIngredients(ingredients);
    }

    public void fillSpinnerWithIngredients(List<String> ingredients) {
        spinner = (Spinner) getView().findViewById(R.id.ingredient_spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, ingredients);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    public void addListenerOnAddIngredientButton() {
        addIngredientButton = (Button) getView().findViewById(R.id.button_add_ingredient);

        addIngredientButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItem() != null){
                    Word word = new Word(String.valueOf(spinner.getSelectedItem()));

                    try {
                        Toast.makeText(getContext(), String.valueOf(spinner.getSelectedItem()), Toast.LENGTH_LONG);
                        mWordViewModel.insert(word);
                        newWord = word;
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    ingredients.remove(word.getWord());
                    fillSpinnerWithIngredients();
                }

            }

        });
    }

    public List<String> getIngredients(){
        return this.ingredients;
    }

    public boolean isIngredient(String ingredient){
        return ingredientList.isIngredient(ingredient);
    }

}
