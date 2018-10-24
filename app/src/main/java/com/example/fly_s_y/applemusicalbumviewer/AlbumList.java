package com.example.fly_s_y.applemusicalbumviewer;

import android.graphics.drawable.BitmapDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds Album data fetched from iTunes
 */
public class AlbumList {
    private List<Album> albumList;
    /**
     * Timestamp of when data was last uodated by iTunes
     */
    private String lastUpdated;

    public AlbumList(List<Album> albumList, String lastUpdated){
        this.albumList = albumList;
        this.lastUpdated = lastUpdated;
    }

    public List<Album> getAlbumList(){
        return this.albumList;
    }

    public String getLastUpdated(){
        return this.lastUpdated;
    }

    /**
     *  Finds albums where the art hasn't loaded and tries to reload them
     */
    public void reloadAlbumArtForAlbumsWhereArtNotLoaded(){
        for(int i = 0; i < albumList.size(); i++){
            Album album = albumList.get(i);

            if(album.getAlbumArt() instanceof BitmapDrawable){
            }
        }
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
