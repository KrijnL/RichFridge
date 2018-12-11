package org.ucll.ti.richfridge;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Ingredient.class}, version = 1)
public abstract class IngredientRoomDatabase extends RoomDatabase {
    public abstract IngredientDAO ingredientDAO();

    private static final String TAG = "IngredientRoomDatabase";

    private static volatile IngredientRoomDatabase INSTANCE;

    static IngredientRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (IngredientRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IngredientRoomDatabase.class, "word_database")
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final IngredientDAO mDao;

        PopulateDbAsync(IngredientRoomDatabase db) {
            mDao = db.ingredientDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Ingredient word = new Ingredient("Hello");
            mDao.insert(word);
            word = new Ingredient("World");
            mDao.insert(word);
            return null;
        }
    }

}
