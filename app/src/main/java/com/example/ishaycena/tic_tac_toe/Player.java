package com.example.ishaycena.tic_tac_toe;

public class Player {
    // image resource in R.mipmap
    private int imgResource;

    /**
     * initiates an instance of a player, with its resource path
     * @param imgResource the resource path to the user's image (X or O [Located in R.MIPMAP])
     */
    public Player(int imgResource) {
        this.imgResource = imgResource;
    }

    public int getImgResource() {
        return imgResource;
    }
}
