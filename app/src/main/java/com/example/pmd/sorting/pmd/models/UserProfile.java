package com.example.pmd.sorting.pmd.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserProfile
{

    // Variables for the profile
    static private int id_user;
    static private String username_user;
    static private String password_user;
    static private int points_user;

    // Getters and Setters
    static public int ID() { return id_user; }
    static public void ID(int value) { id_user = value; }

    static public String Username() { return username_user; }
    static public void Username(String value) { username_user = value; }

    static public String Password() { return password_user; }
    static public void Password(String value) { password_user = value; }

    static public int Points() { return points_user; }
    static public void Points(int value) { points_user = value; }

    static public void Delete()
    {
        id_user = 0;
        username_user = "";
        password_user = "";
        points_user = 0;
    }
}
