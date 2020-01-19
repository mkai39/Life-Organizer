package com.example.lifeorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Calendar extends AppCompatActivity {

    private Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        home = (Button) findViewById(R.id.from_calendar);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V){
                openHome();
            }

        });
    }

    public void openHome(){
        Intent home_intent = new Intent(this,MainActivity.class);
        startActivity(home_intent);
    }


}
