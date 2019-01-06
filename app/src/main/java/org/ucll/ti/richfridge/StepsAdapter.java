package org.ucll.ti.richfridge;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private final LayoutInflater mInflater;
    private DetailViewModel mDetailViewModel;
    private List<String> steps;

    StepsAdapter(Context context, DetailViewModel detailViewModel) {
        mInflater = LayoutInflater.from(context);
        this.mDetailViewModel = detailViewModel;
        this.steps = detailViewModel.getRecipe().getSteps();
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_detail_steps, viewGroup, false);

        return new StepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder stepViewHolder, int i) {
        stepViewHolder.textView.setText(steps.get(i));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void setSteps(List<String> steps){
        this.steps = steps;
    }

    class StepViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView textView;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.steps_bullet);
            textView = itemView.findViewById(R.id.steps_text);
        }
    }
}
