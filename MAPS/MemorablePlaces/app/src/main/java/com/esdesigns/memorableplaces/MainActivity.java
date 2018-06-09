package com.esdesigns.memorableplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        places = new ArrayList<String>();

        places.add("Add a new place");

        Intent intent = getIntent();


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, places);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Check if the position is position 0 (This means we want to add a new place and go to users location)
                // Any other position means we want to go to that location

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                if (position > 0) {
                    intent.putExtra("Location", places.get(position)); // Send coordinates
                }
                startActivity(intent);
            }
        });

    }
}
