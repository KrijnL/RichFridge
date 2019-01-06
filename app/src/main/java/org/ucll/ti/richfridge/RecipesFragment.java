package org.ucll.ti.richfridge;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecipeViewModel mRecipeViewModel;
    private RecipeAdapter adapter;
    private WordViewModel mWordViewModel;
    private List<Recipe> recipes;
    private List<String> ingredients;
    private DetailViewModel mDetailViewModel;

    private boolean favorite;


    private static final String TAG = "recipesFragment";

    // TODO: Rename and change types of parameters



    public RecipesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment RecipesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipesFragment newInstance(boolean favo) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putBoolean("favorite", favo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.favorite = getArguments().getBoolean("favorite", false);
        }
        mRecipeViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        mWordViewModel = ViewModelProviders.of(getActivity()).get(WordViewModel.class);
        mDetailViewModel = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        List<String> ingredients = new ArrayList<>();
        for(Word w : mWordViewModel.getAllWords().getValue()){
            ingredients.add(w.getWord());
        }


        if(favorite) {
            getActivity().setTitle("Favorites");
            recipes = mRecipeViewModel.getFavorites();
        }else{
            getActivity().setTitle("Recipes");
            recipes = mRecipeViewModel.searchRecipes(ingredients);
        }


        adapter = new RecipeAdapter(getContext());

        adapter.setOnItemClickListener(new RecipeAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.e("ONITEMCLICK POS", position+"");
                Recipe r  = recipes.get(position);
                mDetailViewModel.setRecipe(r);
                Toast.makeText(getContext(), r.getTitle(), Toast.LENGTH_LONG).show();
                replaceFragmentWithAnimation(new DetailsFragment(), "Recipes");

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        adapter.setRecipes(recipes);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frament_recipes, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);

        if(favorite) {
            Log.e("ISFAVORITE??", "YES");
            navigationView.setCheckedItem(R.id.nav_favo);
        }else{
            navigationView.setCheckedItem(R.id.nav_recipes);
        }
        RecyclerView recyclerView = view.findViewById(R.id.recipe_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.diveder);

        recyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void setIngredients(List<String> ingredients){
        this.ingredients = ingredients;
    }


}
