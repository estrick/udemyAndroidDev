package com.esdesigns.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Generate random number
        Random rand = new Random();
        n = rand.nextInt(20) + 1;
    }

    public void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void guess(View view) {
        // Get users guess
        EditText userInput = (EditText) findViewById(R.id.userInput);
        int userGuess = Integer.parseInt(userInput.getText().toString());

        if (userGuess > n) {
            makeToast("Lower");
        } else if (userGuess < n) {
            makeToast("Higher");
        } else {
            makeToast("Correct! Guess again!");
            // Generate random number
            Random rand = new Random();
            n = rand.nextInt(20) + 1;
        }
    }

}
