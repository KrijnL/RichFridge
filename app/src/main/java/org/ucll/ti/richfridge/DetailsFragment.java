package org.ucll.ti.richfridge;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.

 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
    private Recipe recipe = null;
    private DetailViewModel mDetailsViewModel;
    private QrCodeViewModel mQrCodeViewModel;
    private RecipeViewModel mRecipeViewModel;

    private ImageView image;
    private TextView title, description;
    private RecyclerView steps, ingredients;

    private StepsAdapter stepsAdapter;
    private IngredientsAdapter ingredientsAdapter;
    private MenuItem favo;


    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mDetailsViewModel = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);
        mQrCodeViewModel = ViewModelProviders.of(getActivity()).get(QrCodeViewModel.class);
        mRecipeViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        setRecipe(mDetailsViewModel.getRecipe());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle(recipe.getTitle());

        stepsAdapter = new StepsAdapter(getContext(), mDetailsViewModel);
        ingredientsAdapter = new IngredientsAdapter(getContext(), mDetailsViewModel);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*Toolbar toolbar = getActivity().findViewById(R.id.toolbar);

        toolbar.inflateMenu(R.menu.menu_toolbar);*/

        image = getActivity().findViewById(R.id.recipe_details_image);
        title = getActivity().findViewById(R.id.detail_title);
        description = getActivity().findViewById(R.id.detail_description);


        steps = getActivity().findViewById(R.id.detail_steps_recyclerview);
        ingredients = getActivity().findViewById(R.id.detail_ingredient_recyclerview);

        steps.setAdapter(stepsAdapter);
        ingredients.setAdapter(ingredientsAdapter);

        steps.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredients.setLayoutManager(new LinearLayoutManager(getContext()));



        title.setText(recipe.getTitle());
        description.setText(recipe.getDescription());

//        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
//        Log.e("TOOLBAR SIZE", toolbar.getMenu().size()+"");
//
//        for(int i = 0; i<toolbar.getMenu().size(); i++){
//            Log.e("LOGGING LOOP", "position : "+i );
//            if(toolbar.getMenu().getItem(i).getItemId() == R.id.action_fav){
//                favo = toolbar.getMenu().getItem(i);
//                Log.e("TOOLBAR", "FAVO FOUND!!!");
//            }
//        }
//        if(recipe.isFavorite()) {
//            favo.setIcon(R.drawable.ic_favorite_red_24dp);
//        }

        new DownloadImageTask(image)
                .execute(recipe.getImageURL());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        Log.e("TOOLBAR SIZE", toolbar.getMenu().size()+"");

        for(int i = 0; i<toolbar.getMenu().size(); i++){
            Log.e("LOGGING LOOP", "position : "+i );
            if(toolbar.getMenu().getItem(i).getItemId() == R.id.action_fav){
                favo = toolbar.getMenu().getItem(i);
                Log.e("TOOLBAR", "FAVO FOUND!!!");
            }
        }
        if(recipe.isFavorite()) {
            favo.setIcon(R.drawable.ic_favorite_red_24dp);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.e("CLICK", item.getTitle().toString());
        switch (item.getItemId()) {
            case R.id.action_fav:

                Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
                String msg = "Added to favorites";
                if(recipe.isFavorite()){
                    msg = "Removed from favorites";
                    toolbar.getMenu().getItem(1).setIcon(R.drawable.ic_favorite_black_24dp);
                }else{
                    toolbar.getMenu().getItem(1).setIcon(R.drawable.ic_favorite_red_24dp);
                }
                mRecipeViewModel.setFavorite(recipe.getId());
                Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_share:
                Toast.makeText(getActivity(), "SHARE", Toast.LENGTH_SHORT).show();
                String text=mDetailsViewModel.getRecipe().getId()+""; ;// Whatever you need to encode in the QR code
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    mQrCodeViewModel.setQrCode(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }

                replaceFragmentWithAnimation(new QrCodeFragment(), recipe.getTitle());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

}
