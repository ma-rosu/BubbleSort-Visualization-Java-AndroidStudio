package com.example.pmd.sorting.pmd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pmd.sorting.pmd.R;
import com.example.pmd.sorting.pmd.models.Numbers;
import com.example.pmd.sorting.pmd.models.UserProfile;
import com.example.pmd.sorting.pmd.modules.DatabaseManager;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

public class SortShowPage extends AppCompatActivity {

    private List<Integer> numbers = new ArrayList<>();
    private List<TextView> views = new ArrayList<>();

    private boolean btnPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sort_show);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numbers.add(Numbers.nr_0);
        numbers.add(Numbers.nr_1);
        numbers.add(Numbers.nr_2);
        numbers.add(Numbers.nr_3);
        numbers.add(Numbers.nr_4);
        numbers.add(Numbers.nr_5);
        numbers.add(Numbers.nr_6);
        numbers.add(Numbers.nr_7);
        numbers.add(Numbers.nr_8);


        views.add(findViewById(R.id.nr0));
        views.add(findViewById(R.id.nr1));
        views.add(findViewById(R.id.nr2));
        views.add(findViewById(R.id.nr3));
        views.add(findViewById(R.id.nr4));
        views.add(findViewById(R.id.nr5));
        views.add(findViewById(R.id.nr6));
        views.add(findViewById(R.id.nr7));
        views.add(findViewById(R.id.nr8));

        for(int i = 0; i < numbers.size(); i++)
        {
            views.get(i).setText(String.valueOf(numbers.get(i)));
        }
    }

    public void Sort(View view) throws SQLDataException {
        if(!btnPressed && !check_if_list_sorted())
        {
            btnPressed = true;
            sortWithDelay(0, view);
            UserProfile.Points(UserProfile.Points()+1);
            DatabaseManager db = new DatabaseManager(this);
            db.open();
            db.update_points(UserProfile.ID(), UserProfile.Points());
            db.close();
        }

    }

    private void sortWithDelay(int i, final View view) {
        if (i >= numbers.size() - 1 && check_if_list_sorted())
        {
            btnPressed = false;
            return;
        }
        else
        {
            if(!check_if_list_sorted() && i >= numbers.size() - 1)
            {
                i = 0;
            }
        }

        final Handler handler = new Handler();
        final int delayMillis = 1000; // Adjust the delay time (in milliseconds) as needed

        TextView v1 = views.get(i);
        TextView v2 = views.get(i + 1);

        // Set initial black color
        v1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.black));
        v2.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.black));
        view.invalidate();

        int n1 = numbers.get(i);
        int n2 = numbers.get(i + 1);

        if (n1 > n2) {
            numbers.set(i, n2);
            numbers.set(i + 1, n1);
            v1.setText(String.valueOf(n2));
            v2.setText(String.valueOf(n1));
        }

        // Post delayed action to change color back to purple
        int finalI = i;
        handler.postDelayed(() -> {
            v1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.purple));
            v2.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.purple));
            view.invalidate();

            // Proceed to the next pair
            sortWithDelay(finalI + 1, view);
        }, delayMillis);
    }

    private boolean check_if_list_sorted()
    {
        for(int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                return false;
            }
        }
        return true;
    }


    public void Click_On_Go_Back(View view)
    {
        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }
}