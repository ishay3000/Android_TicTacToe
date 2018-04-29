package com.example.ishaycena.tic_tac_toe;

public class Point {
    private int x, y;

    /**
     * Represents a point in the game-board
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
