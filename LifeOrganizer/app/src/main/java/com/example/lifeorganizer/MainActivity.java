package com.example.lifeorganizer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

    ImageView imageView;
    Uri imageUri;
    private static int PICK_IMAGE = 100;


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
        imageView = (ImageView)findViewById(R.id.imageView);
        import_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                //   openImport();
                openGallery();
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


    public void openSchedule(){
        Intent schedule_intent = new Intent(this,schedule.class);
        startActivity(schedule_intent);
    }

    public void openCamera(){
        Intent camera_intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(camera_intent);
    }


    private void openGallery() {
        Intent photo_intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(photo_intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (result == RESULT_OK && request == PICK_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }


}
