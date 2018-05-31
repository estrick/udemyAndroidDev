package com.esdesigns.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ArrayList<ArrayList> items;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ImageView imageView;
    LinearLayout linearLayout;

    int answerPositions[] = new int[2];
    String pageContents;

    public void buttonPressed(View view) {
        // Display result in toast
        if(answerPositions[0] == Integer.parseInt(view.getTag().toString())) {
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Incorrect: The player was " + items.get(0).get(answerPositions[1]), Toast.LENGTH_LONG).show();
        }
        // Generate UI
        items = generateArrayLists(pageContents);
        answerPositions = placeItems(items);
    }

    public ArrayList<ArrayList> generateArrayLists(String pageContents) {
        Pattern p = Pattern.compile("<div class=\"player-card__info__name\">(.*?)</div>");
        Matcher m = p.matcher(pageContents);
        // Create two array lists containing player names and their corresponding image sources
        ArrayList<String> players = new ArrayList<>();
        ArrayList<String> srcs = new ArrayList<>();
        // Create Array List to store both array lists
        items = new ArrayList<>();
        while (m.find()) {
            players.add(m.group(1));
        }
        p = Pattern.compile("data-src=\"(.*?)\\?");
        m = p.matcher(pageContents);
        while (m.find()) {
            srcs.add("https://www.arsenal.com" + m.group(1));
        }
        items.add(players); items.add(srcs);
        return items;
    }

    public int[] placeItems(ArrayList<ArrayList> items) {

        // Empty Buttons used to determine which buttons have not been used already
        boolean[] emptyButtons = {true, true, true, true};
        // Unused Players used to determine which players have not been used
        boolean[] unusedPlayers = new boolean[items.get(0).size()];
        // Sets all player values to true
        for(int i = 0; i < unusedPlayers.length; i++) {
            unusedPlayers[i] = !unusedPlayers[i];
        }

        Button currentButton;
        Random randItemPosition = new Random();
        Random randButtonPosition = new Random();
        // answerPositions = {button position, player position}

        while(emptyButtons[0] || emptyButtons[1] || emptyButtons[2] || emptyButtons[3]) {
            int itemPosition = randItemPosition.nextInt(items.get(0).size()); // Randomly generate a number that corresponds to a position in the array
            int buttonPosition = randButtonPosition.nextInt(4); // Randomly generate a number 0-3 corresponding to buttons

            // If all buttons are empty it is the first run through, this is the answer and the corresponding
            // button position generated above is the answer position
            if(emptyButtons[0] && emptyButtons[1] && emptyButtons[2] && emptyButtons[3]) {
                answerPositions[0] = buttonPosition;
                answerPositions[1] = itemPosition;
                ImageDownloader imageDownloader = new ImageDownloader();
                try {
                    imageView.setImageBitmap(imageDownloader.execute((String) items.get(1).get(itemPosition)).get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                currentButton = (Button) linearLayout.getChildAt(buttonPosition);
                currentButton.setText((String) items.get(0).get(itemPosition));
                emptyButtons[buttonPosition] = false;
            } else {
                // If at least one button is not empty we fill the remaining buttons
                if(emptyButtons[buttonPosition] && unusedPlayers[itemPosition]) {
                    currentButton = (Button) linearLayout.getChildAt(buttonPosition);
                    currentButton.setText((String) items.get(0).get(itemPosition));
                    emptyButtons[buttonPosition] = false;
                    unusedPlayers[itemPosition] = false;
                }
            }
        }
        return answerPositions;
    }

    public String parseWebPage() {
        // Get all web page as a string
        WebParser webParser = new WebParser();
        try {
            return webParser.execute("https://www.arsenal.com/first-team/players").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Null";
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "Null";
        }
    }

    public static class WebParser extends AsyncTask<String, Void, String> {

        // Web Parser class to get whole web page as a string, runs on AsyncTask1
        @Override
        protected String doInBackground(String... urls) {

            URL url;
            HttpsURLConnection httpsURLConnection;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                url = new URL(urls[0]);
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
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
                return "Invalid/Unreachable URL";
            } catch (IOException e) {
                e.printStackTrace();
                return "IO Exception";
            }
        }
    }

    public static class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        // ImageDownloader class to download relevant image, runs on AsyncTask2
        @Override
        protected Bitmap doInBackground(String... urls) {

            URL url;
            HttpsURLConnection urlConnection;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();

                return BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise Views
        imageView = (ImageView) findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        // Parse the webpage
        pageContents = parseWebPage();
        // Get the arrays containing names and srcs
        items = generateArrayLists(pageContents);
        // Place the items in buttons & imageviews
        answerPositions = placeItems(items);
    }




}
