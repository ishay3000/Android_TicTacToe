package com.example.ishaycena.tic_tac_toe;

import android.media.Image;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class Tic_Tac_Toe {
     public static Player[] PLAYERS = new Player[2];
     public static Cell[][] GAME_BOARD = new Cell[3][3];
     public static int TURN = 0;


     
     public static void Init(){
         for (int i = 0; i < GAME_BOARD.length; i++) {
             for (int j = 0; j < GAME_BOARD[i].length; j++) {
                 GAME_BOARD[i][j] = new Cell(new Point(i, j));
//                 GAME_BOARD[i][j].setOccupied(false);
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
         // changing the image from blank to X or O
         btn.setImageResource(resource);
         // disabling the button
         btn.setEnabled(false);

         // disable this point in our private game board
         Point point = (Point) btn.getTag();
         // set the cell as occupied
         GAME_BOARD[point.getX()][point.getY()].setOccupied(true);
         // set the cell the player's num
         GAME_BOARD[point.getX()][point.getY()].setPlayerNum(TURN);

         // changing TURN to the next player's turn
         TURN = (PLAYERS.length - 1) - TURN;
     }


    /**
     * Performs a turn by the computer, also changes our private board
     * @return a point for the main activity to disable the corresponding button
     */
    public static Point AITurn(){
         for (int i = 0; i < GAME_BOARD.length; i++) {
             for (int j = 0; j < GAME_BOARD[i].length; j++) {
                 if (!GAME_BOARD[i][j].isOccupied()){
                     return new Point(i, j);
                 }
             }
         }
        return null;
    }


    public static Player CheckForWinners(){
        try {
            // num of rows - 1
            final int rows_total_indexed = 2;

            Cell current;
            Cell previous;

            // Checking Rows
            for (int i = 0; i < rows_total_indexed; i++) {
                if (GAME_BOARD[i][0].getPlayerNum() == GAME_BOARD[i][1].getPlayerNum() && GAME_BOARD[i][1].getPlayerNum() == GAME_BOARD[i][2].getPlayerNum()) {
                    return new Player(PLAYERS[GAME_BOARD[i][0].getPlayerNum()].getImgResource());
                }
            }

            // Checking Columns
            for (int i = 0; i < rows_total_indexed; i++) {
                if (GAME_BOARD[0][i].getPlayerNum() == GAME_BOARD[1][i].getPlayerNum() && GAME_BOARD[1][i].getPlayerNum() == GAME_BOARD[2][i].getPlayerNum()) {
                    return new Player(PLAYERS[GAME_BOARD[0][i].getPlayerNum()].getImgResource());
                }
            }

            // Checking Diagonal
            if (GAME_BOARD[0][0].getPlayerNum() == GAME_BOARD[1][1].getPlayerNum() && GAME_BOARD[1][1].getPlayerNum() == GAME_BOARD[2][2].getPlayerNum()) {
                return new Player(PLAYERS[GAME_BOARD[0][0].getPlayerNum()].getImgResource());
            }

            // Checking anti-diagonal
            if (GAME_BOARD[0][2].getPlayerNum() == GAME_BOARD[1][1].getPlayerNum() && GAME_BOARD[1][1].getPlayerNum() == GAME_BOARD[2][0].getPlayerNum()) {
                return new Player(PLAYERS[GAME_BOARD[0][2].getPlayerNum()].getImgResource());
            }
        }catch (Exception ex){
            Log.d("TICTAC", ex.getMessage());
        }


//        // checking ROWS
//        for (int i = 0; i <= rows_total_indexed; i++) {
//            for (int j = 1; j <= rows_total_indexed; j++) {
//                current = GAME_BOARD[i][(rows_total_indexed - i)];
//                previous = GAME_BOARD[i][(rows_total_indexed - i - 1)];
//
//                // if previous cell is different than the current
//                if (current.getPlayerNum() != previous.getPlayerNum()){
//                    break;
//                }
//                //if we got to the last index, and we didn't break the loop, it means we had a streak
//                if (i == rows_total_indexed){
//                    return new Player(PLAYERS[current.getPlayerNum()].getImgResource());
//                }
//            }
//        }
//
//        // checking COLUMNS
//        for (int i = 0; i <= rows_total_indexed; i++) {
//            for (int j = 1; j <= rows_total_indexed; j++) {
//                current = GAME_BOARD[(rows_total_indexed - i)][i];
//                previous = GAME_BOARD[(rows_total_indexed - i - 1)][i];
//
//                // if previous cell is different than the current
//                if (current.getPlayerNum() != previous.getPlayerNum()){
//                    break;
//                }
//                //if we got to the last index, and we didn't break the loop, it means we had a streak
//                if (i == rows_total_indexed){
//                    return new Player(PLAYERS[current.getPlayerNum()].getImgResource());
//                }
//            }
//        }

        return null;
    }
}