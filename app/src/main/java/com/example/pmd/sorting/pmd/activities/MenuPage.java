package com.example.pmd.sorting.pmd.activities;

import android.content.Intent;
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
import com.example.pmd.sorting.pmd.models.Numbers;
import com.example.pmd.sorting.pmd.models.UserProfile;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void Click_On_Log_Out(View view)
    {
        UserProfile.Delete();
        Intent intent = new Intent(this, SignPage.class);
        startActivity(intent);
    }


    public void Click_On_View_Profile(View view)
    {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    public void Click_On_Sort_The_List(View view)
    {
        EditText et0 = findViewById(R.id.editText0);
        EditText et1 = findViewById(R.id.editText1);
        EditText et2 = findViewById(R.id.editText2);
        EditText et3 = findViewById(R.id.editText3);
        EditText et4 = findViewById(R.id.editText4);
        EditText et5 = findViewById(R.id.editText5);
        EditText et6 = findViewById(R.id.editText6);
        EditText et7 = findViewById(R.id.editText7);
        EditText et8 = findViewById(R.id.editText8);

        if(et0.getText().toString().isEmpty() || et1.getText().toString().isEmpty() ||
                et2.getText().toString().isEmpty() || et3.getText().toString().isEmpty() ||
                et4.getText().toString().isEmpty() || et5.getText().toString().isEmpty() ||
                et6.getText().toString().isEmpty() || et7.getText().toString().isEmpty() ||
                et8.getText().toString().isEmpty())

        {
            Toast.makeText(this, "Please enter all numbers.", Toast.LENGTH_SHORT).show();
        }
        else {
            Numbers.nr_0 = Integer.parseInt((et0.getText().toString()));
            Numbers.nr_1 = Integer.parseInt((et1.getText().toString()));
            Numbers.nr_2 = Integer.parseInt((et2.getText().toString()));
            Numbers.nr_3 = Integer.parseInt((et3.getText().toString()));
            Numbers.nr_4 = Integer.parseInt((et4.getText().toString()));
            Numbers.nr_5 = Integer.parseInt((et5.getText().toString()));
            Numbers.nr_6 = Integer.parseInt((et6.getText().toString()));
            Numbers.nr_7 = Integer.parseInt((et7.getText().toString()));
            Numbers.nr_8 = Integer.parseInt((et8.getText().toString()));

            Intent intent = new Intent(this, SortShowPage.class);
            startActivity(intent);
        }
    }
}