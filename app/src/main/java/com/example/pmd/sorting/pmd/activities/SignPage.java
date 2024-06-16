package com.example.pmd.sorting.pmd.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pmd.sorting.pmd.R;
import com.example.pmd.sorting.pmd.models.UserProfile;
import com.example.pmd.sorting.pmd.modules.DatabaseHelper;
import com.example.pmd.sorting.pmd.modules.DatabaseManager;

public class SignPage extends AppCompatActivity {

    DatabaseManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DatabaseManager(this);
    }

    public void Click_On_Sign_Up(View view)
    {
        EditText name = findViewById(R.id.inputUsername);
        EditText pass = findViewById(R.id.inputPassword);

        if(name.getText().toString().isEmpty() || pass.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please fill in all fields.",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.open();

                Cursor cursor = db.fetch_by_username(name.getText().toString());

                if(cursor.moveToFirst())
                {
                    Toast.makeText(this, "A user with that name already exists.",
                            Toast.LENGTH_LONG).show();
                }
                else
                {

                    db.insert(name.getText().toString(), pass.getText().toString(), 0);
                    Toast.makeText(this, "User registered. You can now sign in.",
                            Toast.LENGTH_LONG).show();
                }
                db.close();

            }
            catch(Exception e)
            {
                db.close();
            }
        }
    }


    @SuppressLint("Range")
    public void Click_On_Sign_In(View view)
    {
        EditText name = findViewById(R.id.inputUsername);
        EditText pass = findViewById(R.id.inputPassword);

        if(name.getText().toString().isEmpty() || pass.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please fill in all fields.",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.open();

                Cursor cursor = db.fetch_by_username(name.getText().toString());

                if(cursor.moveToFirst())
                {
                    if(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD)).equals(pass.getText().toString()))
                    {
                        UserProfile.ID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_ID))));
                        UserProfile.Username(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_USERNAME)));
                        UserProfile.Password(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD)));
                        UserProfile.Points(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_POINTS))));
                        Intent intent = new Intent(this, MenuPage.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(this, "Username or password are incorrect.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "Username or password are incorrect.",
                            Toast.LENGTH_SHORT).show();
                }
                db.close();

            }
            catch(Exception e)
            {
                db.close();
            }
        }
    }
}