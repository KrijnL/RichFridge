package org.ucll.ti.richfridge;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RecipeDbInMemory {

    private static final String TAG = "RecipeDbInMEmory";
    List<Recipe> recipes;

    public RecipeDbInMemory(){

        recipes = new ArrayList<Recipe>();
        List<String> pastaIngredients = new ArrayList<String>();
        List<String> pastaSteps = new ArrayList<String>();
        List<String> pizzaIngredients = new ArrayList<String>();
        List<String> pizzaSteps = new ArrayList<String>();
        List<String> chickenIngredients = new ArrayList<String>();
        List<String> chickenSteps = new ArrayList<String>();
        List<String> friesIngredients = new ArrayList<String>();
        List<String> friesSteps = new ArrayList<String>();
        List<String> salmonIngredients = new ArrayList<String>();
        List<String> salmonSteps = new ArrayList<String>();
        List<String> eggIngredients = new ArrayList<String>();
        List<String> eggSteps = new ArrayList<String>();
        List<String> beefIngredients = new ArrayList<String>();
        List<String> beefSteps = new ArrayList<String>();
        List<String> baconIngredients = new ArrayList<String>();
        List<String> baconSteps = new ArrayList<String>();

        pastaIngredients.add("pasta");
        pastaIngredients.add("chicken");
        pastaIngredients.add("eggs");

        pastaSteps.add("Lorem ipsum dolor sit amet");
        pastaSteps.add("consectetur adipiscing elit");
        pastaSteps.add("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");

        pizzaIngredients.add("chicken");
        pizzaIngredients.add("eggs");
        pizzaIngredients.add("brocolli");

        pizzaSteps.add("Lorem ipsum dolor sit amet");
        pizzaSteps.add("consectetur adipiscing elit");
        pizzaSteps.add("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");

        chickenIngredients.add("chicken");
        chickenIngredients.add("milk");

        chickenSteps.add("Lorem ipsum dolor sit amet");
        chickenSteps.add("consectetur adipiscing elit");
        chickenSteps.add("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");

        friesIngredients.add("potato");

        friesSteps.add("Lorem ipsum dolor sit amet");
        friesSteps.add("consectetur adipiscing elit");
        friesSteps.add("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");

        salmonIngredients.add("salmon");
        salmonIngredients.add("brocolli");

        salmonSteps.add("Lorem ipsum dolor sit amet");
        salmonSteps.add("consectetur adipiscing elit");
        salmonSteps.add("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");

        eggIngredients.add("eggs");
        eggIngredients.add("bacon");
        eggIngredients.add("milk");

        eggSteps.add("Lorem ipsum dolor sit amet");
        eggSteps.add("consectetur adipiscing elit");
        eggSteps.add("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");

        beefIngredients.add("beef");
        beefIngredients.add("onion");

        beefSteps.add("Lorem ipsum dolor sit amet");
        beefSteps.add("consectetur adipiscing elit");
        beefSteps.add("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");

        baconIngredients.add("bacon");
        baconIngredients.add("onion");

        baconSteps.add("Lorem ipsum dolor sit amet");
        baconSteps.add("consectetur adipiscing elit");
        baconSteps.add("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");

        Recipe pasta = new Recipe("Pasta", "a lovely pasta",
                "https://cdn-image.myrecipes.com/sites/default/files/styles/medium_2x/public/image/lighten-up-america/pasta-vodka-cream-sauce-ck-x.jpg",pastaIngredients,pastaSteps);

        Recipe pizza = new Recipe( "Pizza", "Classical italian dish",
                "https://www.philips.be/c-dam/b2c/kitchen-machine/landing-page/pizza-thumbnail.jpg",pizzaIngredients,pizzaSteps);

        Recipe chicken = new Recipe( "Chicken", "Tasteful chicken",
                "https://www.newvision.co.ug/w-images/df488e17-840e-4d6a-8fca-6fbe29f5cf7a/1/aaaaaaasmall-703x422.jpg",chickenIngredients,chickenSteps);

        Recipe fries = new Recipe( "Fries", "Classical Belgian fries",
                "https://recipes.timesofindia.com/photo/54659021.cms",friesIngredients,friesSteps);

        Recipe salmon = new Recipe( "Salmon", "Delicious salmon from the north sea",
                "https://static01.nyt.com/images/2016/04/13/dining/13PAIRING/13PAIRING-articleLarge.jpg",salmonIngredients,salmonSteps);

        Recipe eggs = new Recipe( "Eggs", "Tasty eggs",
                "https://cdn-image.myrecipes.com/sites/default/files/styles/medium_2x/public/field/image/sunny-side-up-eggs-hero.jpg?itok=V2aQ9L_N",eggIngredients,eggSteps);

        Recipe beef = new Recipe( "Beef", "Beefy beef",
                "https://www.thespruceeats.com/thmb/B9QreYUY9AYTPaUYWaDTFZM4e0E=/1500x998/filters:fill(auto,1)/slow-grilled-beef-ribs-335856-Hero-5b8c4b33c9e77c007bc64595.jpg",beefIngredients,beefSteps);

        Recipe bacon = new Recipe( "Bacon", "Fat bacon",
                "https://truffle-assets.imgix.net/d1862c0e-202-baconwithsugar-dishland2.jpg",baconIngredients,baconSteps);

        add(pasta);
        add(pizza);
        add(chicken);
        add(fries);
        add(salmon);
        add(eggs);
        add(beef);
        add(bacon);
    }

    public void add(Recipe recipe){

        //if no ID assigned, assign ID automatically
        if(recipe.getId()==-1) {

            if (recipes.size() != 0) {
                recipe.setId(recipes.get(recipes.size() - 1).getId() + 1);
            } else {
                recipe.setId(0);
            }
        }else{
            for(Recipe r : recipes){
                if(r.getId() == recipe.getId()){
                    Log.e(TAG, "can't add same ID twice");
                }
            }
        }
        recipes.add(recipe);
    }

    public void delete(Recipe recipe){
        recipes.remove(recipe);
    }

    public void update(Recipe recipe){
        for(Recipe r : recipes){
            if(r.getId() == recipe.getId()){
                delete(r);
                recipes.add(recipe.getId(),recipe);
            }
        }
    }

    public Recipe get(int id){
        for(Recipe r : recipes){
            if(r.getId() == id){
                return r;
            }
        }
        Log.e(TAG, "cannot find a recipe with that id");
        return null;
    }

    public List<Recipe> getAll() {return recipes;}

    public void setFavorite(int id){
        for(Recipe r : recipes){
            if(r.getId() == id){
                r.setFavorite(!r.isFavorite());
            }
        }
    }
}
