package com.esdesigns.demoapp;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void loginAttempt(View view) {
        EditText username = (EditText) findViewById(R.id.usernameField);
        EditText password = (EditText) findViewById(R.id.passwordField);
        // Display username and password in the logs
        Log.i("Log In Attempt", ("Username: " + username.getText().toString()) +
                ", Password: " + password.getText().toString());
        Toast.makeText(MainActivity.this, "Username: " + username.getText().toString() +
                "\nPassword: " + password.getText().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
