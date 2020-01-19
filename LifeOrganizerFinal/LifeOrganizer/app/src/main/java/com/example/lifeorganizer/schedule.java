package com.example.lifeorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class schedule extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        lv = (ListView) findViewById(R.id.event_list);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("July 13 Incredibles 2");
        arrayList.add("September 29 11 a.m-5 p.m. Fall Harvest Festival");
        ArrayAdapter adapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(adapt);
    }
}
