package com.example.osvaldoairon.challengeeeagle.SQLITE;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;


public class UserDB extends SQLiteOpenHelper{

    public static final String NAME_DATABASE="USERRECIPESxSxxxYx";
    public static final int VERSION_DATABASE  = 19;
    public static final String NAME_TABLE = "recipesxUsersxxxxzy";
    public static final String ID_DATABASE="_id";

    public static final String USER_NAME= "username";
    public static final String USER_PASSWORD = "userpassword";
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_LOGIN = "user_login";
    public static final String USER_ENABLED = "user_enabled";

    public UserDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            /**
              THIS CONSTRUCTOR DEFAULT OF SQLITEOPENHELTER
             NECESSITY ONLY CONEXT, NAME OF DATABASE , CURSOR FACTOR AND VERSION
             FACTORY NOT IS NECESSARY TODAY;
             */
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + NAME_TABLE + " ( "+ ID_DATABASE + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ USER_NAME + " TEXT NOT NULL, "
                +
        USER_PASSWORD + " TEXT, " + USER_EMAIL + " TEXT NOT NULL, " + USER_LOGIN + " TEXT NOT NULL," + USER_ID + "INTEGER, " +USER_ENABLED+ " TEXT NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
