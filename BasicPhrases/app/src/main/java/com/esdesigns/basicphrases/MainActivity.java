package com.esdesigns.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public void buttonPressed(View view) {

        int intID =  view.getId();
        String stringID = view.getResources().getResourceEntryName(intID);

        int resourceID = getResources().getIdentifier(stringID, "raw", "com.esdesigns.basicphrases");

        MediaPlayer mediaPlayer = MediaPlayer.create(this, resourceID);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
