package android.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    // Player representation
    // 0 - X
    // 1 - o
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //  Satae meanings
    // o - X
    // 1 - o
    // 2 - Null
    int[][] winPositions = {{0,1,2}, {0,3,6}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};

    public void Playertap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }

        if(gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O`s Turn: Tap To Play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X`s Turn: Tap To Play");
            }
            img.animate().translationYBy(1000f).setDuration(200);
        }
        // when no player has won the game
        else {
            gameActive = true;
            gameReset(view);
        }
        // check if any player has won the game
        for (int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2){
                // someone has won - Let's find who it is
                String winnerStr = null;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X has Won";
                }
                else if(gameState[winPosition[0]] == 1){
                    winnerStr = "O has Won";
                }
                else{
                    gameActive = true;
                    gameReset(view);
                    winnerStr = "No one has won";
                }
                //update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X`s Turn: Tap To Play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}