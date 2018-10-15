package com.example.fly_s_y.applemusicalbumviewer;

import java.util.List;

public class AlbumList {
    private List<Album> albumList;
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

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
