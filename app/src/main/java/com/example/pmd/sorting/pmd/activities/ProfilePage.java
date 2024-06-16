package com.example.pmd.sorting.pmd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pmd.sorting.pmd.R;
import com.example.pmd.sorting.pmd.models.UserProfile;
import com.example.pmd.sorting.pmd.modules.DatabaseManager;

public class ProfilePage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView profile_username = findViewById(R.id.txtUsername);
        TextView profile_points = findViewById(R.id.txtPoints);


        profile_username.setText(UserProfile.Username());
        profile_points.setText(Integer.toString(UserProfile.Points()));
    }

    public void Click_On_Go_Back(View view)
    {
        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }

    public void Click_On_Change_Password(View view) {
        EditText new_password = findViewById(R.id.inputPassword);
        if(new_password.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please enter a new password.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try
            {
                DatabaseManager db = new DatabaseManager(this);
                db.open();
                db.update_password(UserProfile.ID(), new_password.getText().toString());
                db.close();
                UserProfile.Password(new_password.getText().toString());
                Toast.makeText(this, "Password changed successfully.", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, "An error has occurred Password has not changed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}