package com.esdesigns.braintrainer;

import android.app.ActionBar;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // View variables
    Button startButton;
    Button button0, button1, button2, button3;
    LinearLayout linearLayout;
    TableLayout tableLayout;
    CountDownTimer countDownTimer;
    TextView countdownText, problemText, scoreText, resultText;

    // Game variables
    final long gameTimeLimit = 30000;
    int score;
    boolean gameRunning;
    int answer; // Answer to the problem
    Random num1, num2; // Two numbers to operate on
    Random numA, numB, numC; // Random wrong values



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        countdownText = (TextView) findViewById(R.id.countdownText);
        problemText = (TextView) findViewById(R.id.problemText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        resultText = (TextView) findViewById(R.id.resultText);

    }


    public void startGame(View view) {

        // Disable visibility of start button, enable for rest of UI
        startButton = (Button) view;
        startButton.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        tableLayout.setVisibility(View.VISIBLE);

        CountDownTimer countDownTimer = new CountDownTimer(gameTimeLimit + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCountDownText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                updateCountDownText(gameTimeLimit);
            }
        }.start();

    }

    public void problemAnswered(View view) {

    }

    public void updateCountDownText(long countDownTime) {
        countdownText.setText(String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(countDownTime) % 60,
                TimeUnit.MILLISECONDS.toSeconds(countDownTime) % 60));
    }




}
