package com.example.fly_s_y.applemusicalbumviewer;

import android.graphics.Bitmap;

/**
 * Class representing a single album
 */
public class Album {
    private String albumName, artistName;
    private Bitmap albumImage;

    public Album(String albumName, String artistName, Bitmap albumImage){
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumImage = albumImage;
    }

    public String getAlbumName(){
        return albumName;
    }

    public String getArtistName(){
        return artistName;
    }

    public Bitmap getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(Bitmap albumImage) {
        this.albumImage = albumImage;
    }
}
