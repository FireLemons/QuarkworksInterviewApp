package com.example.fly_s_y.applemusicalbumviewer;

/**
 * Class representing a single album
 */
public class Album {
    private String albumName, artistName, imageLink;

    public Album(String albumName, String artistName, String imageLink){
        this.albumName = albumName;
        this.artistName = artistName;
        this.imageLink = imageLink;
    }

    public String getAlbumName(){
        return albumName;
    }

    public String getArtistName(){
        return artistName;
    }

    public String getImageLink(){
        return imageLink;
    }
}
