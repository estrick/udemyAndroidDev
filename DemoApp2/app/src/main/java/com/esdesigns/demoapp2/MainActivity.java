package com.esdesigns.demoapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginAttempt(View view) {

        // Username and password variables
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        // Create toast containing username and password
        Toast.makeText(this, "Username: " + username.getText().toString() +
                "\nPassword: " + password.getText().toString(), Toast.LENGTH_LONG).show();

    }

}
