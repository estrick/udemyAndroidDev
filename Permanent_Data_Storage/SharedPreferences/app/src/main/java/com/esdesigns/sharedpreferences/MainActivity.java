package com.esdesigns.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.esdesigns.sharedpreferences", Context.MODE_PRIVATE);

        //sharedPreferences.edit().putString("Username", "elliot").apply();

        //String username = sharedPreferences.getString("Username", "username");

        //Log.i("Username: ", username);

        ArrayList<String> family = new ArrayList<String>();
        family.add("Mum"); family.add("Dad");

        try {
            sharedPreferences.edit().putString("family", ObjectSerializer.serialize(family)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newFamily = new ArrayList<String>();

        try {
            newFamily = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("family", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("New Family: ", newFamily.toString());


    }
}
