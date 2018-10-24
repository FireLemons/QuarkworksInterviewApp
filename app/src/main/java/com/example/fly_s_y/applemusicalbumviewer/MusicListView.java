package com.example.fly_s_y.applemusicalbumviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.fly_s_y.Request.AppleMusicRequestHandler;

public class MusicListView extends AppCompatActivity {

    private AlbumAdapter albumAdapter;
    private AlbumList albumListData;
    private AppleMusicRequestHandler handler;
    private ErrorDisplay error;
    private RecyclerView albumList;
    private View loadBar;
    private View loadOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list_view);

        albumList = findViewById(R.id.album_list_view);
        loadBar = findViewById(R.id.loadBar);
        loadOverlay = findViewById(R.id.loadOverlay);
        albumListData = new AlbumList(null, null);
        albumAdapter = new AlbumAdapter(albumListData);
        error = new ErrorDisplay();

        albumList.setLayoutManager(new LinearLayoutManager(this));
        albumList.setAdapter(albumAdapter);

        handler = new AppleMusicRequestHandler(error);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBar.setVisibility(View.VISIBLE);

        if(albumListData.getAlbumList() == null || albumListData.getAlbumList().isEmpty()){
            loadOverlay.setVisibility(View.VISIBLE);
        }

        if(true){//handler.isConnection()){
            handler.getAlbumData(albumAdapter, albumListData, this, loadOverlay, loadBar);
        } else if(albumListData.getAlbumList() == null || albumListData.getAlbumList().isEmpty()){
            loadBar.setVisibility(View.GONE);
            loadOverlay.setVisibility(View.GONE);
            error.setError("Failed to connect to iTunes' RSS feed");
        } else {
            loadBar.setVisibility(View.GONE);
            error.setWarning("Could not refresh album data.");
        }
    }

    public ErrorDisplay getError(){
        return this.error;
    }
}
