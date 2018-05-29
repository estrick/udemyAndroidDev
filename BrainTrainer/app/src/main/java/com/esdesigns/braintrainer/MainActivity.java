package com.esdesigns.braintrainer;

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
    Button button0, button1, button2, button3, restartButton;
    LinearLayout linearLayout;
    TableLayout tableLayout;
    CountDownTimer countDownTimer;
    TextView countdownText, problemText, scoreText, resultText;

    // Game variables
    final long gameTimeLimit = 30000;
    int score = 0;
    boolean gameRunning;
    int answer; // Answer to the problem
    Random rand1, rand2; // Two numbers to operate on
    int num1, num2;
    Random randA, randB, randC; // Random wrong values
    int numA, numB, numC;
    Random randOperation; // Division/Multiplication/Addition/Subtraction
    int operator; // 0 = multiplication, 1 = addition, 2 = subtraction
    Random rollDiceForButton;
    int dice;
    boolean[] emptyBoxes = {false, false, false, false};



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

        rand1 = new Random(); rand2 = new Random();
        randA = new Random(); randB = new Random(); randC = new Random();
        randOperation = new Random();
        rollDiceForButton = new Random();

        // Initialise Buttons
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        restartButton = (Button) findViewById(R.id.restartButton);
    }


    public void createProblem() {

        operator = randOperation.nextInt(2); // Get the operation
        num1 = rand1.nextInt(20) + 1; // First number
        num2 = rand2.nextInt(20) + 1; // Second number

        // Get solution
        switch (operator) {
            case 0: // Multiplication
                problemText.setText(String.format(Locale.getDefault(), "%d * %d", num1, num2));
                answer = num1 * num2;
                break;
            case 1: // Addition
                problemText.setText(String.format(Locale.getDefault(), "%d + %d", num1, num2));
                answer = num1 + num2;
                break;
            case 2: // Subtraction
                if (num1 >= num2) {
                    problemText.setText(String.format(Locale.getDefault(), "%d - %d", num1, num2));
                    answer = num1 - num2;
                } else {
                    problemText.setText(String.format(Locale.getDefault(), "%d - %d", num2, num1));
                    answer = num2 - num1;
                }
                break;
        }

        // Get random wrong answers
        boolean noNumbersEqualAnswer = true;
        numA = randA.nextInt(answer) + 20;
        numB = randB.nextInt(answer) + 20;
        numC = randC.nextInt(answer) + 20;
        if(numA == answer || numB == answer || numC == answer){
            noNumbersEqualAnswer = false;
        }
        while (!noNumbersEqualAnswer) {
            if (numA == answer) {
                numA = randA.nextInt(answer) + 20;
            } else if(numB == answer) {
                numB = randB.nextInt(answer) + 20;
            } else if(numC == answer) {
                numC = randC.nextInt(answer) + 20;
            }
            if(numA != answer && numB != answer && numC != answer){
                noNumbersEqualAnswer = true;
            }
        }
        // Set button texts
        emptyBoxes[0] = false; emptyBoxes[1] = false; emptyBoxes[2] = false; emptyBoxes[3] = false;
        emptyBoxes = rollDice(answer, emptyBoxes);
        for (int i = 0; i < 3; i++) {
            if(emptyBoxes[i]) {
                // Add values to the boxes from 0 - 3, skipping the answer box
                switch (i) {
                    case 0:
                        button1.setText(String.format(Locale.getDefault(), "%d", numA));
                        button2.setText(String.format(Locale.getDefault(), "%d", numB));
                        button3.setText(String.format(Locale.getDefault(), "%d", numC));
                        break;
                    case 1:
                        button0.setText(String.format(Locale.getDefault(), "%d", numA));
                        button2.setText(String.format(Locale.getDefault(), "%d", numB));
                        button3.setText(String.format(Locale.getDefault(), "%d", numC));
                        break;
                    case 2:
                        button0.setText(String.format(Locale.getDefault(), "%d", numA));
                        button1.setText(String.format(Locale.getDefault(), "%d", numB));
                        button3.setText(String.format(Locale.getDefault(), "%d", numC));
                        break;
                    case 3:
                        button0.setText(String.format(Locale.getDefault(), "%d", numA));
                        button1.setText(String.format(Locale.getDefault(), "%d", numB));
                        button2.setText(String.format(Locale.getDefault(), "%d", numC));
                        break;
                }
            }
        }

    }

    public boolean[] rollDice(int value, boolean[] emptyBoxes) {
        // Randomly place the answer
        dice = rollDiceForButton.nextInt(3);
        switch (dice) {
            case 0:
                button0.setText(String.format(Locale.getDefault(), "%d", value));
                break;
            case 1:
                button1.setText(String.format(Locale.getDefault(), "%d", value));
                break;
            case 2:
                button2.setText(String.format(Locale.getDefault(), "%d", value));
                break;
            case 3:
                button3.setText(String.format(Locale.getDefault(), "%d", value));
                break;
        }
        emptyBoxes[dice] = true;
        return emptyBoxes;
    }

    public void startGame(View view) {

        // Clear variables from previous game (if applies)
        score = 0;
        resultText.setText("");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        // Disable visibility of start button, enable for rest of UI
        startButton = (Button) view;
        startButton.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        tableLayout.setVisibility(View.VISIBLE);
        resultText.setVisibility(View.VISIBLE);


        scoreText.setText(String.format(Locale.getDefault(), "%d", score));
        createProblem();

        CountDownTimer countDownTimer = new CountDownTimer(gameTimeLimit + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCountDownText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                updateCountDownText(gameTimeLimit);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                resultText.setText(String.format(Locale.getDefault(), "Game Complete - Score: %d", score));
                restartButton.setVisibility(View.VISIBLE);
            }
        }.start();



    }

    public void problemAnswered(View view) {

        // Determine which button has been pressed and get the ID
        Button buttonClicked = (Button) view;
        int ID = buttonClicked.getId();
        String readableID = buttonClicked.getResources().getResourceEntryName(ID);

        // Determine which button box has the correct answer & update score accordingly
        if(readableID.equals("button0") && emptyBoxes[0]) {
            resultText.setText(R.string.correct);
            score++;
        } else if(readableID.equals("button1") && emptyBoxes[1]) {
            resultText.setText(R.string.correct);
            score++;
        } else if(readableID.equals("button2") && emptyBoxes[2]) {
            resultText.setText(R.string.correct);
            score++;
        } else if(readableID.equals("button3") && emptyBoxes[3]) {
            resultText.setText(R.string.correct);
            score++;
        } else {
            resultText.setText(R.string.wrong);
        }

        scoreText.setText(String.format(Locale.getDefault(), "%d", score));
        createProblem();

    }

    public void updateCountDownText(long countDownTime) {
        countdownText.setText(String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(countDownTime) % 60,
                TimeUnit.MILLISECONDS.toSeconds(countDownTime) % 60));
    }




}
