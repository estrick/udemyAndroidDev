package com.esdesigns.videodemo;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView stack = (VideoView) findViewById(R.id.videoView);
        stack.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.stack);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(stack);
        stack.setMediaController(mediaController);
        stack.start();
    }
}
