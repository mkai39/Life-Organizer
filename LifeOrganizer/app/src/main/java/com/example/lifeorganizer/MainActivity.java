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
    private Button import_photo;
    private Button schedule;
    private Button camera;

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

        import_photo = (Button) findViewById(R.id.Import);
        import_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openImport();
            }
        });

        schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openSchedule();
            }
        });


        camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openCamera();
            }
        });


    }

    public void openCalendar(){
        Intent calendar_intent = new Intent(this,Calendar.class);
        startActivity(calendar_intent);
    }

    public void openImport(){
        Intent import_intent = new Intent(this,Import_photo.class);
        startActivity(import_intent);
    }

    public void openSchedule(){
        Intent schedule_intent = new Intent(this,schedule.class);
        startActivity(schedule_intent);
    }

    public void openCamera(){
        Intent camera_intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(camera_intent);
    }

}
