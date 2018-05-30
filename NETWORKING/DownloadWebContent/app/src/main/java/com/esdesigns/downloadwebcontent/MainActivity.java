package com.esdesigns.downloadwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.ECField;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // Look up AsyncTask
    // Look up var args

    public class DownloadTask extends AsyncTask<String, Void, String> {  // <URL, METHOD FOR PROGRESS OF DL, TYPE OF VAR THAT WILL BE RETURNED>
        // Task because it is doing something
        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null; // Used as a browser to fetch contents of URL

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream(); // Stream to hold input of data as it arrives
                InputStreamReader reader = new InputStreamReader(in); // Used to read the input stream
                int data = reader.read(); // Keeps track of location through the html content we are on
                while (data != -1) { // Once all data is read data will have a value of -1
                    char currentChar = (char) data;
                    result += currentChar; // Adding all characters of the html to result string
                    data = reader.read(); // Moves to next character of the url
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        try {
            String result = task.execute("http://www.stratfordnaturaltherapies.com.au").get();
            Log.i("Contents of URL: ", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
