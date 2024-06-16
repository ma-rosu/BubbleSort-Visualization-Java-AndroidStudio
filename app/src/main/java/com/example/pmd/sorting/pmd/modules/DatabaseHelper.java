package com.example.pmd.sorting.pmd.modules;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{

    static final String DATABASE_NAME = "PMD.DB";
    static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE = "USERS";
    public static final String USER_ID = "user_id";
    public static final String USER_USERNAME = "user_username";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_POINTS = "user_points";

    private static final String CREATE_DB_QUERY = "create table " + DATABASE_TABLE +
            " (" + USER_ID + " integer primary key autoincrement, " + USER_USERNAME +
            " text not null, " + USER_PASSWORD + " text not null, " + USER_POINTS + ");";


    public DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
