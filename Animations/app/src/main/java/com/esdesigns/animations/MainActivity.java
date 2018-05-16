package com.esdesigns.animations;

import android.media.Image;
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

//    public void fade2man(View view) {
//
//        ImageView mmeImg = (ImageView) findViewById(R.id.mmeImage);
//        ImageView manImg = (ImageView) findViewById(R.id.man);
//
//        mmeImg.animate().alpha(0f).setDuration(1000);
//        manImg.animate().alpha(1f).setDuration(1000);
//
//    }
//
//    public void fade2mme(View view) {
//
//        ImageView mmeImg = (ImageView) findViewById(R.id.mmeImage);
//        ImageView manImg = (ImageView) findViewById(R.id.man);
//
//        manImg.animate().alpha(0f).setDuration(1000);
//        mmeImg.animate().alpha(1f).setDuration(1000);
//
//    }

    public void moveMan(View view) throws InterruptedException {

        ImageView man = (ImageView) findViewById(R.id.moveMe);
        //man.animate().rotationX(360f).setDuration(1000); //Back flip
        //man.animate().rotation(360f).setDuration(1000); // Spin
        //man.animate().translationXBy(1000f).setDuration(2000);
        man.animate()
                .scaleX(0.1f)
                .scaleY(0.1f)
                .rotation(720f)
                .setDuration(2000); // Shrink
    }

}
