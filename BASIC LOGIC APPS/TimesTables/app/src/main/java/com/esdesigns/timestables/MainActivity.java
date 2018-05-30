package com.esdesigns.timestables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> timesTables;
    ArrayAdapter<String> arrayAdapter;
    int min = 1;
    int timesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generate random number to start slider at
        Random random = new Random();
        int startValue = random.nextInt(20) + 1;

        // Create slider, set max equal to 20 and set current position to the start value
        SeekBar sliderBar = (SeekBar) findViewById(R.id.sliderBar);
        sliderBar.setMax(20);
        sliderBar.setProgress(startValue);

        // Populate timesTables ArrayList with times tables, convert to Array Adapter, attach to List View
        timesTables = timesTables(startValue);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTables);
        final ListView listView = (ListView) findViewById(R.id.timesTableListView);
        listView.setAdapter(arrayAdapter);


        // SeekBar Listener Methods
        sliderBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress < min) {
                    timesTable = min;
                } else {
                    timesTable = progress;
                }
                seekBar.setProgress(timesTable);
                timesTables = timesTables(timesTable);
                arrayAdapter.clear();
                arrayAdapter.addAll(timesTables);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public ArrayList<String> timesTables(int progress) {
        timesTables = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            timesTables.add(Integer.toString(progress * i));
        }
        return timesTables;
    }

}
