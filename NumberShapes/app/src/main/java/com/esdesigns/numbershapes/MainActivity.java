package com.esdesigns.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkNumber(View view) {

        EditText userInput = (EditText) findViewById(R.id.userInput);
        TextView resultText = (TextView) findViewById(R.id.resultText);

        if(userInput.getText().toString().isEmpty()){
            String s = "Please enter a number";
            resultText.setText(s);
        } else {
            Number aNum = new Number();
            aNum.number = Integer.parseInt(userInput.getText().toString());

            if (aNum.isTriangular() && aNum.isSquare(aNum.number)) {
                String s = aNum.number + " is triangular and square.";
                resultText.setText(s);
            } else if (aNum.isTriangular()) {
                String s = aNum.number + " is triangular.";
                resultText.setText(s);
            } else if (aNum.isSquare(aNum.number)) {
                String s = aNum.number + " is square.";
                resultText.setText(s);
            } else {
                String s = aNum.number + " is neither triangular nor square.";
                resultText.setText(s);
            }
        }

    }

    public class Number {

        int number;

        private boolean isTriangular() {
            return isSquare(8*number + 1);
        }

        private boolean isSquare(int x) {
            return Math.sqrt(x)==(int)Math.sqrt(x);
        }


    }


}
