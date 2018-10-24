package com.example.fly_s_y.applemusicalbumviewer;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Class representing a single album
 */
public class Album extends BaseObservable {
    private String albumName, artistName, albumArtURL;
    private Drawable albumArt;

    public Album(String albumArtURL, String albumName, String artistName, Drawable albumArt, Resources resources){
        this.albumArtURL = albumArtURL;
        this.albumName = albumName;
        this.artistName = artistName;
        if(albumArt != null){
            this.albumArt = albumArt;
        } else {
            this.albumArt = new BitmapDrawable(BitmapFactory.decodeResource(resources, R.mipmap.broken_image));
        }
    }

    public String getAlbumArtURL(){
        return albumArtURL;
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
    public Drawable getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(BitmapDrawable albumArt) {
        this.albumArt = albumArt;
        notifyPropertyChanged(BR.albumArt);
    }
}
