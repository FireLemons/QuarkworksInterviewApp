package com.example.fly_s_y.applemusicalbumviewer;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;

/**
 * Class representing a single album
 */
public class Album extends BaseObservable {
    private String albumName, artistName;
    private Bitmap albumImage;

    public Album(String albumName, String artistName, Bitmap albumImage){
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumImage = albumImage;
    }

    @Bindable
    public String getAlbumName(){
        return albumName;
    }

    @Bindable
    public String getArtistName(){
        return artistName;
    }

    @Bindable
    public Bitmap getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(Bitmap albumImage) {
        this.albumImage = albumImage;
        notifyPropertyChanged(BR.albumImage);
    }
}
