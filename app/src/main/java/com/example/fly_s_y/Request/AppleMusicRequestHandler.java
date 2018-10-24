package com.example.fly_s_y.Request;

import com.example.fly_s_y.applemusicalbumviewer.Album;
import com.example.fly_s_y.applemusicalbumviewer.AlbumAdapter;
import com.example.fly_s_y.applemusicalbumviewer.AlbumList;
import com.example.fly_s_y.applemusicalbumviewer.ErrorDisplay;
import com.example.fly_s_y.applemusicalbumviewer.MusicListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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
                errorDisplay.setError(e.toString());
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

                    String updated = (String)getValue(data, ".feed.updated");

                    if(!updated.equals(albumList.getLastUpdated())){
                        loadUpdatedAlbumData(data);
                        albumList.setLastUpdated(updated);
                    }

                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity.dismissLoadBar();
                            mainActivity.dismissLoadOverlay();
                        }
                    });
                } catch (JSONException ex){
                    errorDisplay.setError(ex.getMessage());
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

                    Album albumData = new Album(
                            (String)getValue(albumJSON, ".artworkUrl100"),
                            (String)getValue(albumJSON, ".name"),
                            (String)getValue(albumJSON, ".artistName"),
                            null,
                            mainActivity.getResources()
                    );

                    albums.add(albumData);
                    albumData.loadAlbumArt(adapter, i, mainActivity);
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
}