package com.esdesigns.connect3;

import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Grid 3x3 Array
    int[][] grid = { {0,0,0}, {0,0,0}, {0,0,0} };
    int player;
    boolean gameActive = true;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Randomly choose which player goes first
        Random rand = new Random();
        player = rand.nextInt(2) + 1;

    }

    public boolean checkForWinner() {
        return (grid[0][0] == player && grid[0][1] == player && grid[0][2] == player) // Top Row
                || (grid[1][0] == player && grid[1][1] == player && grid[1][2] == player) // Middle Row
                || (grid[2][0] == player && grid[2][1] == player && grid[2][2] == player) // Bottom Row
                || (grid[0][0] == player && grid[1][0] == player && grid[2][0] == player) // Left Col
                || (grid[0][1] == player && grid[1][1] == player && grid[2][1] == player) // Middle Col
                || (grid[0][2] == player && grid[1][2] == player && grid[2][2] == player) // Right Col
                || (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) // Top Left - Bottom Right Diagonal
                || (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player); // Top Right - Bottom Left Diagonal
    }

    public void restart(View view) {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = 0;
            }
        }
        for (int i = 0; i < 9; i++) {
            android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        gameActive = true;

    }

    public void updateGame(ImageView counter) {
        // Update grid with player number
        switch (counter.getId()) {
            case R.id.topLeft:
                updateGridElement(counter,0,0);
                break;
            case R.id.topCentre:
                updateGridElement(counter,0,1);
                break;
            case R.id.topRight:
                updateGridElement(counter,0,2);
                break;
            case R.id.centreLeft:
                updateGridElement(counter,1,0);
                break;
            case R.id.centreCentre:
                updateGridElement(counter,1,1);
                break;
            case R.id.centreRight:
                updateGridElement(counter,1,2);
                break;
            case R.id.bottomLeft:
                updateGridElement(counter,2,0);
                break;
            case R.id.bottomCentre:
                updateGridElement(counter,2,1);
                break;
            case R.id.bottomRight:
                updateGridElement(counter,2,2);
                break;
        }
    }

    public void updateGridElement(ImageView counter ,int row, int col) {
        if(grid[row][col] == 0) {
            grid[row][col] = player;
            // Move counter, set image and switch player
            counter.setTranslationY(-1000f);
            if (player == 1) {
                counter.setImageResource(R.drawable.red);
            } else {
                counter.setImageResource(R.drawable.yellow);
            }
            counter.animate().translationYBy(1000f).setDuration(300);
        }
    }

    public String createGridMessage() {
        msg = " \n" + grid[0][0] + " " + grid[0][1] + " " + grid[0][2] + "\n"
                + grid[1][0] + " " + grid[1][1] + " " + grid[1][2] + "\n"
                + grid[2][0] + " " + grid[2][1] + " " + grid[2][2];
        return msg;
    }

    public void dropIn(View view) {
        // Views
        ImageView counter = (ImageView) view;
        TextView information = (TextView) findViewById(R.id.information);
        // Update game
        updateGame(counter);
        msg = createGridMessage();
        Log.i("Board: ", msg); // Display grid in logs

        // Check for winner
        if (checkForWinner()) {
            if (player == 1) {
                information.setText(R.string.winner1);
            } else {
                information.setText(R.string.winner2);
            }
            gameActive = false;
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.bringToFront();
        } else {
            if(player == 1) {
                player = 2;
            } else {
                player = 1;
            }
        }

    }


}
