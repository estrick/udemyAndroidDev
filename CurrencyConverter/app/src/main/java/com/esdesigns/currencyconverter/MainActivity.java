package com.esdesigns.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.nio.DoubleBuffer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonSelected(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Get input from text field and convert to double for currency conversion
        EditText userInput = (EditText) findViewById(R.id.userInput);
        double amountEntered = Double.parseDouble(userInput.getText().toString());
        double convertedAmount = 0;

        // Initialise toast string
        String toastString = "";

        // Conversion Rates
        final double AUD2GBP = 0.56;
        final double AUD2USD = 0.75;
        final double AUD2JPY = 82.49;
        final double AUD2RMB = 4.77;
        final double AUD2EURO = 0.63;

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.gbp:
                if (checked)
                    // Convert to GBP
                    convertedAmount = amountEntered*AUD2GBP;
                    toastString = "£";
                    break;
            case R.id.usd:
                if (checked)
                    // Convert to USD
                    convertedAmount = amountEntered*AUD2USD;
                    toastString = "$";
                    break;
            case R.id.jpy:
                if (checked)
                    // Convert to JPY
                    convertedAmount = amountEntered*AUD2JPY;
                    toastString = "¥";
                    break;
            case R.id.rmb:
                if (checked)
                    // Convert to RMB
                    convertedAmount = amountEntered*AUD2RMB;
                    toastString = "¥";
                    break;
            case R.id.euro:
                if (checked)
                    // Convert to EURO
                    convertedAmount = amountEntered*AUD2EURO;
                    toastString = "€";
                    break;
        }

        // Display converted amount in Toast
        Toast.makeText(this,  amountEntered + " AUD = " + toastString + " " +
                convertedAmount, Toast.LENGTH_SHORT).show();


    }

}
