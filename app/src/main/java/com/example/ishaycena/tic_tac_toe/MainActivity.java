package com.example.ishaycena.tic_tac_toe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout[] gameBoard;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    ImageButton[][] buttons = new ImageButton[3][3];
    View.OnClickListener listener;
    Tic_Tac_Toe tic_tac_toe;
    TextView tv;


    //stroke
    //border
    //ado circle dependency android - dependency
    //ado circle image view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        params.leftMargin = 100;
        params.topMargin = 70;

        InitGameBoard(MainActivity.this);
    }


    /**
     * initiates the PLAYERS array in Tic_Tac_Toe.PLAYERS
     */
    protected void InitPlayers(){
        Tic_Tac_Toe.PLAYERS[0] = new Player(R.mipmap.ic_circle);
        Tic_Tac_Toe.PLAYERS[1] = new Player(R.mipmap.ic_x);
    }

    /**
     * initiates the game board (linear layout(s))
     * @param ctx context to be displayed in
     */
    protected void InitGameBoard(final Context ctx) {
        LinearLayout layout = findViewById(R.id.lnMain);
        gameBoard = new LinearLayout[3];
        InitPlayers();
        Tic_Tac_Toe.Init();

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof ImageButton) {
                    Tic_Tac_Toe.PlayTurn((ImageButton) v);

                    try {
                        Player player = Tic_Tac_Toe.CheckForWinners();


                        if (player != null) {
                            int circle = R.mipmap.ic_circle;
                            int X = R.mipmap.ic_x;
                            int result = -1;
                            if (player.getImgResource() == circle) {
                                Toast.makeText(MainActivity.this, "The winner is the Circle !", Toast.LENGTH_LONG).show();
                            } else if (player.getImgResource() == X) {
                                Toast.makeText(MainActivity.this, "The winner is the X !", Toast.LENGTH_LONG).show();
                            }

                            result = (player.getImgResource() == circle ? circle : X);

                            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                            intent.putExtra("GAME_RESULT", result);
                            startActivity(intent);
                        }
                    }catch (Exception ex){
                        Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    // disabling the window
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    final Point AI_Coord = Tic_Tac_Toe.AITurn();


                    tv.setText("Thinking... how should I play... ?");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText("");
                            if (AI_Coord != null) {
                                Tic_Tac_Toe.PlayTurn(buttons[AI_Coord.getX()][AI_Coord.getY()]);

                                // enabling window touches after the computer finished playing
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                        }
                    }, 1000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Player player1 = Tic_Tac_Toe.CheckForWinners();

                            if (player1 != null) {
                                int circle = R.mipmap.ic_circle;
                                int X = R.mipmap.ic_x;
                                if (player1.getImgResource() == circle) {
                                    Toast.makeText(MainActivity.this, "The winner is the Circle !", Toast.LENGTH_LONG).show();
                                } else if (player1.getImgResource() == X) {
                                    Toast.makeText(MainActivity.this, "The winner is the X !", Toast.LENGTH_LONG).show();
                                }

                                int result = (player1.getImgResource() == circle ? circle : X);
                                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                                intent.putExtra("GAME_RESULT", result);
                                startActivity(intent);
                            }
                        }
                    }, 1500);

                }
            }
        };

        Drawable drawable = this.getResources().getDrawable(R.mipmap.ic_blank_sq);

        for (int i = 0; i < gameBoard.length; i++) {
            gameBoard[i] = new LinearLayout(ctx);
            gameBoard[i].setLayoutParams(params);

            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j] = new ImageButton(ctx);
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setImageResource(R.mipmap.ic_blank_space);
                //buttons[i][j].setBackground(getResources().getDrawable(R.mipmap.ic_blank_sq));
                buttons[i][j].setBackgroundColor(Color.WHITE);
                buttons[i][j].setTag(new Point(i, j));
                buttons[i][j].setOnClickListener(listener);

                gameBoard[i].addView(buttons[i][j]);
            }
            layout.addView(gameBoard[i]);
        }

        tv = new TextView(MainActivity.this);
        tv.setLayoutParams(params);
        tv.setText("");
        layout.addView(tv);
    }
}
