package com.esdesigns.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String language;
    TextView languageText;
    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.english:
                setLanguage("English");
                return true;
            case R.id.spanish:
                setLanguage("Spanish");
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languageText = (TextView) findViewById(R.id.languageText);
        sharedPreferences = getSharedPreferences("com.esdesigns.languagepreferences", Context.MODE_PRIVATE);

        language = sharedPreferences.getString("language", null);

        if (language == null) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Language Preference")
                    .setMessage("Choose your language")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("Spanish");
                        }
                    })
                    .show();
        } else {
            switch(language) {
                case "English":
                    languageText.setText(R.string.english);
                    break;
                case "Spanish":
                    languageText.setText(R.string.spanish);
                    break;
            }
        }
    }

    public void setLanguage(String l) {
        language = l;
        if (language.equals("English")) {
            languageText.setText(R.string.english);
        } else if (language.equals("Spanish")) {
           languageText.setText(R.string.spanish);
        }
        sharedPreferences.edit().putString("language", language).apply();
    }
}
