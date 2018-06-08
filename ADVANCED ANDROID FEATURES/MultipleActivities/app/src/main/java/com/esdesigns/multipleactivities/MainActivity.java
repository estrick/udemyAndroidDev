package com.esdesigns.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void buttonClicked(View view) {
        Button buttonClicked = (Button) view;
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("name", buttonClicked.getText());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
