package org.ucll.ti.richfridge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
        import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    private final LayoutInflater mInflater;
    private DetailViewModel mDetailViewModel;
    private List<String> ingredients;

    IngredientsAdapter(Context context, DetailViewModel detailViewModel) {
        mInflater = LayoutInflater.from(context);
        this.mDetailViewModel = detailViewModel;
        this.ingredients = detailViewModel.getRecipe().getIngredients();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_detail_ingredients, viewGroup, false);

        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder stepViewHolder, int i) {
        stepViewHolder.textView.setText(ingredients.get(i));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setIngredients(List<String> steps){
        this.ingredients = steps;
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.ingredient_recycler_text);
        }
    }
}
