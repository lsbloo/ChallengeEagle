package com.example.osvaldoairon.challengeeeagle.SQLITE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecipesDB  extends SQLiteOpenHelper{


    public static final String NAME_DATABASE="recipesxxYDB";
    public static final String NAME_TABLE = "recipesxXs";
    public static final String ID_COLUMN = "_id";
    public static final int VERSION_DATABASE = 4;

    public static final String NAME_IMG = "images";
    public static final String NAME_DISH = "names";
    public static final String DESCRIPTION_DISH = "description";
    public static final String MAKE_DISH = "make";
    public static final String MASK_USER = "maskuser";



    public RecipesDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NAME_DATABASE, factory, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + NAME_TABLE + " ( " + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ NAME_IMG + " BLOB, " +
        NAME_DISH + " TEXT NOT NULL, "+ DESCRIPTION_DISH + " TEXT, " + MAKE_DISH + " TEXT, " + MASK_USER + " TEXT NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
