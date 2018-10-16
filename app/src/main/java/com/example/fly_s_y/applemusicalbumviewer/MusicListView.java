package com.example.fly_s_y.applemusicalbumviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.fly_s_y.JSON.AppleMusicRequestHandler;

public class MusicListView extends AppCompatActivity {

    private AlbumAdapter albumAdapter;
    private AlbumList albumListData;
    private AppleMusicRequestHandler handler;
    private ErrorDisplay error;
    private RecyclerView albumList;
    private View loadScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list_view);

        albumList = findViewById(R.id.album_list_view);
        loadScreen = findViewById(R.id.loadScreen);
        albumListData = new AlbumList(null, null);
        albumAdapter = new AlbumAdapter(albumListData);
        error = new ErrorDisplay();

        albumList.setLayoutManager(new LinearLayoutManager(this));
        albumList.setAdapter(albumAdapter);

        handler = new AppleMusicRequestHandler(error);
    }

    @Override
    protected void onResume() {
        loadScreen.setVisibility(View.VISIBLE);
        super.onResume();

        if(handler.isConnection()){
            handler.getAlbumData(albumAdapter, albumListData, this, loadScreen);

            if(error.getIsVisible()){
                error.clearError();
            }
        } else if(!error.getIsVisible()){
            error.setError("Could not connect to iTunes' RSS feed");
        }
    }

    public ErrorDisplay getError(){
        return this.error;
    }
}
