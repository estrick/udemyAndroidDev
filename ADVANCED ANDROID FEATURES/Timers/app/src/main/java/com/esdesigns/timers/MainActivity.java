package com.esdesigns.timers;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         HANDLER AND RUNNABLE
        // Handler allows chunks of code to be run in a delayed way
        // i.e every second/ every 5 seconds etc (Period)
        final Handler handler = new Handler();
        // Events/ chunks of code to be run are Runnable's
        Runnable run = new Runnable() {
            @Override
            public void run() {
                // Insert code to be run every period
                // Below code runs this Runnable and delays it 1000ms
                // It runs every second
                handler.postDelayed(this, 1000);
                Log.i("Runnable has run!", "A second has passed");
            }
        };
        handler.post(run); // Initialize Runnable



        // COUNTDOWN TIMER
        // Takes (time to countdown from, period of ticks)
        // Below runs from 10 seconds at 1 second intervals
        new CountDownTimer(10000, 1000) {
            public void onTick(long milliSecondsUntilDone) {
                // Runs every tick (i.e every second in this case)
                Log.i("Seconds left: ", String.valueOf(milliSecondsUntilDone/1000));
            }

            public void onFinish() {
                // When countdown is finished, this runs
                Log.i("Seconds left: ", "Done");
            }

        }.start(); // initialises the timer





    }
}
