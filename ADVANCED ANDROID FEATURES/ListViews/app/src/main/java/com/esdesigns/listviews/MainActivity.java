package com.esdesigns.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        final ArrayList<String> goodCunts = new ArrayList<>();
        goodCunts.add("Sudarshan"); goodCunts.add("Sam"); goodCunts.add("Freddy");
        goodCunts.add("Jacob"); goodCunts.add("Daniel"); goodCunts.add("Jasmin");
        goodCunts.add("Bec");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, goodCunts);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Sup " + goodCunts.get(position), Toast.LENGTH_SHORT).show();
            }
        });


//        // Create listView variable
//        ListView listView = findViewById(R.id.listView);
//
//        // Create ArrayList and add items
//        final ArrayList<String> myFamily = new ArrayList<>();
//        myFamily.add("Zoe"); myFamily.add("David"); myFamily.add("Elliot");
//        myFamily.add("Jacob"); myFamily.add("Freddy");
//
//        // Convert ArrayList into ArrayAdapter with (context, layout, ArrayList)
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFamily);
//        // Attach ArrayAdapter to ListView
//        listView.setAdapter(arrayAdapter);
//
//        // Create click listener and attach to ListView items
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // Can work on the parent ListView using parent.X
//                // View is the item that has been tapped
//                // Position is the number of the item that has been tapped
//                // id is the id of the item
//                Log.i("Family Member: ", myFamily.get(position));
//            }
//        });
    }
}
