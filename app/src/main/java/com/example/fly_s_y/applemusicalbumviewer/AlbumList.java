package com.example.fly_s_y.applemusicalbumviewer;

import java.util.List;

/**
 * Holds Album data fetched from iTunes
 * and metadata corresponding to the album data
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
     *  Launches requests to reload album art for each album where the art has not yet been loaded
     */
    public void loadAlbumArtForAlbumsWhereArtNotLoaded(AlbumAdapter adapter, MusicListView mainActivity){
        for(int i = 0; i < albumList.size(); i++){
            Album album = albumList.get(i);

            if(album.getAlbumArt() == null){
                album.loadAlbumArt(adapter, i, mainActivity);
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
