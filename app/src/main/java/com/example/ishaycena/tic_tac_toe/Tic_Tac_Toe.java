package com.example.ishaycena.tic_tac_toe;

import android.media.Image;
import android.widget.ImageButton;

import org.jetbrains.annotations.NotNull;

public class Tic_Tac_Toe {
     public static Player[] PLAYERS = new Player[2];
     public static boolean[][] GAME_BOARD = new boolean[3][3];
     public static int TURN = 0;


     
     public static void Init(){
         for (int i = 0; i < GAME_BOARD.length; i++) {
             for (int j = 0; j < GAME_BOARD[i].length; j++) {
                 GAME_BOARD[i][j] = false;
             }
         }
     }

    /**
     * performs a TURN, changes the states of the button, and changes the TURN
     * @param btn the button which was clicked
     */
     public static void PlayTurn(@NotNull ImageButton btn){
         // image path
         int resource = PLAYERS[TURN].getImgResource();
         // changing TURN to the next player's turn
         TURN = (PLAYERS.length - 1) - TURN;
         // changing the image from blank to X or O
         btn.setImageResource(resource);
         // disabling the button
         btn.setEnabled(false);

         // disable this point in our private game board
         Point point = (Point) btn.getTag();
         GAME_BOARD[point.getX()][point.getY()] = true;
     }


    /**
     * Performs a turn by the computer, also changes our private board
     * @return a point for the main activity to disable the corresponding button
     */
    public static Point AITurn(){
         for (int i = 0; i < GAME_BOARD.length; i++) {
             for (int j = 0; j < GAME_BOARD[i].length; j++) {
                 if (!GAME_BOARD[i][j]){
                     return new Point(i, j);
                 }
             }
         }
        return null;
    }
}