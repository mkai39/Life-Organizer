package com.example.lifeorganizer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
<<<<<<< HEAD
    }
=======

    }

    public void openHome(){
        Intent home_intent = new Intent(this,MainActivity.class);
        startActivity(home_intent);
    }

    public void addEvent(){

        life_event l = new life_event(3,2,2020, "temp");
        

    }




>>>>>>> 09e355d41775d0effbbb92c311c69e4d06cd49bb
}
