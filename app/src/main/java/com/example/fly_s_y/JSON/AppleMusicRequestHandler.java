package com.example.fly_s_y.JSON;

import android.util.Log;

import com.example.fly_s_y.applemusicalbumviewer.ErrorDisplay;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Handles requesting JSON from the iTunes RSS feed
 */
public class AppleMusicRequestHandler extends JSONFetcher{
    public AppleMusicRequestHandler(ErrorDisplay errorDisplay){
        super("https://", "rss.itunes.apple.com", errorDisplay);
    }

    public void getAlbumData(){
        fetchJSON("/api/v1/us/apple-music/top-albums/all/10/explicit.json", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                errorDisplay.setError(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("RESPONSE IS MEEEEEEE", response.body().string());
            }
        });
    }
}
