package com.example.fly_s_y.Request;

import android.app.Activity;
import android.graphics.BitmapFactory;

import com.example.fly_s_y.applemusicalbumviewer.Album;
import com.example.fly_s_y.applemusicalbumviewer.AlbumAdapter;
import com.example.fly_s_y.applemusicalbumviewer.ErrorDisplay;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AlbumArtRequestHandler extends RequestFetcher {

    public AlbumArtRequestHandler(String domain, ErrorDisplay errorDisplay) {
        super("https://", domain, errorDisplay);
    }

    public void loadAlbumImage(final AlbumAdapter albumAdapter, final Album album, final int albumIndex, String imageURLPath, final Activity mainActivity){
        fetchRequest(imageURLPath, new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                errorDisplay.setError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                album.setAlbumImage(BitmapFactory.decodeStream(response.body().byteStream()));

                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        albumAdapter.notifyItemChanged(albumIndex);
                    }
                });
            }
        });
    }
}
