package com.example.fly_s_y.Request;

import android.util.Log;

import com.example.fly_s_y.applemusicalbumviewer.Album;
import com.example.fly_s_y.applemusicalbumviewer.AlbumAdapter;
import com.example.fly_s_y.applemusicalbumviewer.AlbumList;
import com.example.fly_s_y.applemusicalbumviewer.Genre;
import com.example.fly_s_y.applemusicalbumviewer.MusicListView;
import com.example.fly_s_y.Error.ErrorDisplay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Handles requesting JSON from the iTunes RSS feed
 */
public class AppleMusicRequestHandler extends JSONRequestFetcher {

    public AppleMusicRequestHandler(ErrorDisplay errorDisplay){
        super("https://", "rss.itunes.apple.com", errorDisplay);
    }

    /**
     * Fetches JSON data for the top 10 albums in iTunes and loads it into album models
     * @param adapter The adapter for rendering the data as a list in a recycleview
     * @param albumList The Object holding the parsed JSON data
     * @param mainActivity The activity containing the Recycleview displaying the albums
     */
    public void getAlbumData(final AlbumAdapter adapter, final AlbumList albumList, final MusicListView mainActivity){
        fetchRequest("/api/v1/us/apple-music/top-albums/all/10/explicit.json", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(!e.getMessage().equals("Canceled")){
                    errorDisplay.setError(e.toString());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();

                //Error check
                if(responseString == null || responseString.length() == 0){
                    errorDisplay.setError("Empty response on attempt to fetch album data.");
                    return;
                }

                Object data;

                try{
                    //Determine whether to instantiate JSON string as Object or Array
                    switch(responseString.charAt(0)){
                        case '[':
                            data = new JSONArray(responseString);
                            break;
                        case '{':
                            data = new JSONObject(responseString);
                            break;
                        default:
                            throw new JSONException("Could not convert response to JSON Object");
                    }

                    //Timestamp of when top 10 albums were updated by iTunes
                    String updated = (String)getValue(data, ".feed.updated");

                    //If the current timestamp does not match the timestamp from the response
                    if(!updated.equals(albumList.getLastUpdated())){
                        loadUpdatedAlbumData(data);
                        albumList.setLastUpdated(updated);
                    }

                    albumList.loadAlbumArtForAlbumsWhereArtNotLoaded(adapter, mainActivity);

                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity.dismissLoadBar();
                            mainActivity.dismissLoadOverlay();
                        }
                    });
                } catch (JSONException ex){
                    errorDisplay.setError("Error parsing JSON data into Album.\n" + ex.getStackTrace());
                } 
            }

            /**
             * Loads updated album data into bound models
             * @param data The updated parsed JSON object containing album data
             * @throws JSONException
             */
            private void loadUpdatedAlbumData(Object data) throws JSONException {
                JSONArray albumsData = (JSONArray) getValue(data, ".feed.results");
                ArrayList<Album> albums = new ArrayList<>();

                for(int i = 0; i < albumsData.length(); i++){
                    Object albumJSON = albumsData.get(i);

                    boolean isExplicit = false;

                    try{
                        isExplicit = Boolean.parseBoolean((String)getValue(albumJSON, ".contentAdvisoryRating"));
                    } catch (JSONException e){
                        Log.d("Null explicit?", e.getMessage());
                    }

                    Album albumData = new Album(
                            isExplicit,
                            (String)getValue(albumJSON, ".artworkUrl100"),
                            (String)getValue(albumJSON, ".name"),
                            (String)getValue(albumJSON, ".url"),
                            (String)getValue(albumJSON, ".artistName"),
                            (String)getValue(albumJSON, ".artistUrl"),
                            (String)getValue(albumJSON, ".copyright"),
                            parseITunesDate((String)getValue(albumJSON, ".releaseDate")),
                            null,
                            getGenres((JSONArray)getValue(albumJSON, ".genres"))
                    );

                    albums.add(albumData);
                }

                albumList.setAlbumList(albums);

                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    /**
     * Cancels all active requests created by this class
     * @param albums The object containing the list of album models
     */
    public void cancelAllRequests(List<Album> albums){
        AppleMusicRequestHandler.super.cancelAllRequests();

        if(albums != null){
            for(Album album : albums){
                album.cancelArtRequest();
            }
        }
    }

    /**
     * Parses a date string from iTunes into a Date object
     * @param dateString a string in the form YYYY-MM-DD
     * @return the data contained in dateString as Date object
     */
    private Date parseITunesDate(String dateString){
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try{
            date = parser.parse(dateString);
        }catch(ParseException ex){
            errorDisplay.setWarning("Could not parse album release date.\n" + ex.getStackTrace());
        }

        return date;
    }

    /**
     * Converts a JSONArray of genres as JSONObjects to an ArrayList of Genre objects
     * @param genreList The list of genres to be converted as a JSONArray
     * @return an ArrayList of Genre objects for each genre successfully converted
     */
    private List<Genre> getGenres(JSONArray genreList){
        List<Genre> genres = new ArrayList<>();

        for(int i = 0; i < genreList.length(); i++){
            JSONObject genre;

            //Try casting each element in the JSONArray as a genre.
            try{
               genre = (JSONObject) genreList.get(i);
            } catch (ClassCastException ex){
                errorDisplay.setWarning("Failed casting non JSON object as music genre.\n" + ex.getStackTrace());
                continue;
            } catch (JSONException ex){
                errorDisplay.setWarning("Failed getting music genre JSONObject from JSONArray.\n" + ex.getStackTrace());
                continue;
            }

            //Try mapping the JSONObject to a genre object.
            try{
                genres.add(new Genre((String)getValue(genre, ".name"), (String)getValue(genre, ".url")));
            } catch(ClassCastException ex){
                errorDisplay.setWarning("Failed casting music genre field as a string from a JSON object.\n" + ex.getStackTrace());
                continue;
            } catch(JSONException ex){
                errorDisplay.setWarning("Failed getting music genre field from a JSON object. \n" + ex.getStackTrace());
                continue;
            }
        }

        return genres;
    }
}