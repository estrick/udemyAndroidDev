package com.esdesigns.whatstheweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView weatherText;
    EditText cityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherText = (TextView) findViewById(R.id.weatherText);
        cityEditText = (EditText) findViewById(R.id.cityInput);


    }

    public void checkWeather(View view) {

        DownloaderTask task = new DownloaderTask();
        String cityText = cityEditText.getText().toString();
        // Capitalise first letter of user input
        cityText = cityText.substring(0, 1).toUpperCase() + cityText.substring(1);
        // Download page data
        try {
            String webContent = task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + cityText + "&appid=00f071642f90481d90330aba62fb4ba1").get();
            JSONObject jsonObject = new JSONObject(webContent);

            String descriptionData = jsonObject.getString("weather");
            String mainData = jsonObject.getString("main");
            ArrayList<String> descriptionArrayList = new ArrayList<>();
            ArrayList<String> mainArrayList = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(descriptionData);

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject part = jsonArray.getJSONObject(i);
                descriptionArrayList.add(part.getString("main"));
                descriptionArrayList.add(part.getString("description"));
            }

            Pattern p = Pattern.compile("temp\":(.*?),");
            Matcher m = p.matcher(mainData);
            while (m.find()) {
                mainArrayList.add(m.group(1));
            }
            p = Pattern.compile("humidity\":(.*?),");
            m = p.matcher(mainData);
            while (m.find()) {
                mainArrayList.add(m.group(1));
            }

            mainArrayList.set(0, String.valueOf(Double.parseDouble(mainArrayList.get(0)) - 273.15));

            weatherText.setText(String.format(Locale.getDefault(), "%s: %s\nTemp: %s \u00b0C\nHumidity: %s %%",
                    descriptionArrayList.get(0), descriptionArrayList.get(1),
                    String.format(Locale.getDefault(), "%.1f", Double.parseDouble(mainArrayList.get(0))), mainArrayList.get(1)));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static class DownloaderTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            URL url;
            StringBuilder stringBuilder = new StringBuilder();
            HttpURLConnection urlConnection;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                while (data != -1) {
                    char currentChar = (char) data;
                    stringBuilder.append(currentChar);
                    data = reader.read();
                }
                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }
    }


}
