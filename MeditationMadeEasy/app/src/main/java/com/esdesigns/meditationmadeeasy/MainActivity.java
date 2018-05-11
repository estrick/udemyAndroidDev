package com.esdesigns.meditationmadeeasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void discoverClicked(View view) {

        ImageView imageView = (ImageView) findViewById(R.id.mmeImage);

        imageView.setImageResource(R.drawable.man);
    }
}
