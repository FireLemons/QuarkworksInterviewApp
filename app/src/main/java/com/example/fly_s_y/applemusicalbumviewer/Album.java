package com.example.fly_s_y.applemusicalbumviewer;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Class representing a single album
 */
public class Album extends BaseObservable {
    private String albumName, artistName;
    private Drawable albumImage;

    public Album(String albumName, String artistName, Drawable albumImage, Resources resources){
        this.albumName = albumName;
        this.artistName = artistName;
        if(albumImage != null){
            this.albumImage = albumImage;
        } else {
            this.albumImage = new BitmapDrawable(BitmapFactory.decodeResource(resources, R.mipmap.broken_image));
        }
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
    public Drawable getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(BitmapDrawable albumImage) {
        this.albumImage = albumImage;
        notifyPropertyChanged(BR.albumImage);
    }
}
