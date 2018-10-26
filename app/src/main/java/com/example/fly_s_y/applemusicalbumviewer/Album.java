package com.example.fly_s_y.applemusicalbumviewer;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.fly_s_y.Request.AlbumArtRequestHandler;

import org.json.JSONException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing a single album
 */
public class Album extends BaseObservable {
    private String albumArtURL, albumName, artistName;
    private Drawable albumArt;
    private AlbumArtRequestHandler artFetcher;

    public Album(String albumArtURL, String albumName, String artistName, Drawable albumArt, Resources resources){
        this.albumArtURL = albumArtURL;
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumArt = albumArt;
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

    /**
     * Loads the album art into the album's model
     * @param adapter The adapter used to render the album data in the UI
     * @param albumIndex The index of the album in the albumAdapter's list of albums
     * @param mainActivity The activity containing the album list
     * @throws JSONException
     */
    public void loadAlbumArt(AlbumAdapter adapter, int albumIndex, MusicListView mainActivity) {
        ErrorDisplay errorDisplay = mainActivity.getError();

        if(albumArtURL == null || albumArtURL.length() == 0){
            errorDisplay.setWarning("Could not find album image source.");
        }

        Pattern websitePath = Pattern.compile("https://(is[0-9]-ssl\\.mzstatic\\.com)(.*)");
        Matcher urlParser = websitePath.matcher(albumArtURL);

        if(urlParser.find() && urlParser.groupCount() >= 2){
            artFetcher = new AlbumArtRequestHandler(urlParser.group(1), errorDisplay);
            if(artFetcher.isConnection()){
                artFetcher.loadAlbumImage(adapter, this, albumIndex, urlParser.group(2), mainActivity);
            } else {
                errorDisplay.setWarning("Failed to load album art.");
            }
        } else {
            errorDisplay.setError("Error parsing album art url.");
        }
    }

    public void cancelArtRequest(){
        if(artFetcher != null){
            artFetcher.cancelAllRequests();
        }
    }
}
