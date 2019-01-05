package org.ucll.ti.richfridge;

import java.util.ArrayList;
import java.util.List;

public class IngredientList {

    private List<String> ingredients;

    public IngredientList(){
        ingredients = new ArrayList<String>();
        add("chicken");
        add("beef");
        add("onion");
        add("potato");
        add("brocolli");
        add("salmon");
        add("milk");
        add("bacon");
        add("eggs");

    }

    public void add(String ingredient){
        ingredients.add(ingredient);
    }

    public void remove(String ingredient){
        ingredients.remove(ingredient);
    }

    public List<String> getAll(){
        return ingredients;
    }

    public boolean isIngredient(String ingredient){
        for(String s : ingredients){
            if(s.equals(ingredient)){
                return true;
            }
        }
        return false;
    }
}
