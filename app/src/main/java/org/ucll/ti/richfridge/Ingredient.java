package org.ucll.ti.richfridge;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.regex.Pattern;


@Entity(tableName = "ingredient_table")
public class Ingredient {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ingredient")
    private String mName;



    public Ingredient(String name){
        setName(name);
    }

    public String getName(){
        return this.mName;
    }

    public void setName(String name){
        final Pattern namePattern = Pattern.compile("^[A-Za-z]++$");

        if(name != null && !name.trim().isEmpty() && namePattern.matcher(name).matches()){
            this.mName = name;
        }
        throw new IllegalArgumentException("Ingredient name cannot be empty and must consist of one word.");
    }


}
