package com.example.lifeorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private Button calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = (Button) findViewById(R.id.calendar);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View V){
                openCalendar();
            }

        });
    }

    public void openCalendar(){
        Intent calendar_intent = new Intent(this,Calendar.class);
        startActivity(calendar_intent);
    }

}
