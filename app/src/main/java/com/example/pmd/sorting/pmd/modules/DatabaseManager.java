package com.example.pmd.sorting.pmd.modules;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.sql.SQLDataException;

public class DatabaseManager
{
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx)
    {
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException
    {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public void insert(String username, String password, int points)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_USERNAME, username);
        contentValues.put(DatabaseHelper.USER_PASSWORD, password);
        contentValues.put(DatabaseHelper.USER_POINTS, points);
        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    // Fetches all the data
    public Cursor fetch_all()
    {
        String[] columns = new String[] {DatabaseHelper.USER_ID, DatabaseHelper.USER_USERNAME,
                DatabaseHelper.USER_PASSWORD, DatabaseHelper.USER_POINTS};
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, null,
                null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // Fetches data by username
    public Cursor fetch_by_username(String username_to_find)
    {
        String[] columns = new String[] {DatabaseHelper.USER_ID, DatabaseHelper.USER_USERNAME,
                DatabaseHelper.USER_PASSWORD, DatabaseHelper.USER_POINTS};
        String selection = DatabaseHelper.USER_USERNAME + " = ?";
        String[] selectionArgs = {username_to_find};
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, selection,
                selectionArgs, null, null, null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // Fetches data by id
    public Cursor fetch_by_id(int id_to_find)
    {
        String[] columns = new String[] {DatabaseHelper.USER_ID, DatabaseHelper.USER_USERNAME,
                DatabaseHelper.USER_PASSWORD, DatabaseHelper.USER_POINTS};
        String selection = DatabaseHelper.USER_ID + " = ?";
        String[] selectionArgs = {Integer.toString(id_to_find)};
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, selection,
                selectionArgs, null, null, null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // Updates data
    public int update_password(long id, String password)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_PASSWORD, password);
        int ret = database.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper.USER_ID + "=" + id, null);
        return ret;
    }

    public int update_points(long id, long points)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_POINTS, points);
        int ret = database.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper.USER_ID + "=" + id, null);
        return ret;
    }

    public void delete(long id)
    {
        database.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.USER_ID + "=" + id, null);
    }

}
