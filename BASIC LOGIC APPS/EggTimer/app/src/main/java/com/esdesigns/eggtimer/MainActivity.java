package com.esdesigns.eggtimer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private boolean running = false;
    private CountDownTimer countDownTimer;
    private SeekBar slider;
    private TextView countDownText;
    private MediaPlayer mediaPlayer;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slider = (SeekBar) findViewById(R.id.slider);
        countDownText = (TextView) findViewById(R.id.countDownText);
        slider.setMax(180);
        slider.setProgress(30);
        countDownText.setText(setCountDownText((long) slider.getProgress() * 1000));

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countDownText.setText(setCountDownText((long) slider.getProgress() * 1000));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.air_horn);
    }

    public void startStop(View view) {

         // Enable/Disable slider
        slider.setEnabled(running);
        running = !running; // Toggle running variable
        goButton = (Button) view;
        if(running){
            goButton.setText(R.string.stop);
        } else {
            goButton.setText(R.string.go);
        }

        // Start/ Stop countdown timer depending on state of running
        final long countDownTime = slider.getProgress() * 1000; // Get countdown time in ms
        if (running) {
            countDownTimer = new CountDownTimer(countDownTime +100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countDownText.setText(setCountDownText(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    // Re-enable slider, reset countdown text
                    slider.setEnabled(running);
                    running = !running;
                    // Play sound
                    mediaPlayer.start();
                    countDownText.setText(setCountDownText((long) slider.getProgress() * 1000));
                    goButton.setText(R.string.go);
                }
            }.start();
        } else {
            countDownTimer.cancel(); // Cancel the timer, re-enable slider if the timer has been stopped
            countDownText.setText(setCountDownText((long) slider.getProgress() * 1000));
            goButton.setText(R.string.go);
        }

    }


    public String setCountDownText(long val) {
        return String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(val) % 60,
                TimeUnit.MILLISECONDS.toSeconds(val) % 60);
    }
}
