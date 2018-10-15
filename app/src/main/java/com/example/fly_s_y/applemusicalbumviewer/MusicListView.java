package com.example.fly_s_y.applemusicalbumviewer;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.fly_s_y.JSON.AppleMusicRequestHandler;
import com.example.fly_s_y.applemusicalbumviewer.databinding.ActivityMusicListViewBinding;

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

        ActivityMusicListViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_music_list_view);

        albumList = findViewById(R.id.album_list_view);
        loadScreen = findViewById(R.id.loadScreen);
        error = new ErrorDisplay();
        albumListData = new AlbumList(null, null);
        albumAdapter = new AlbumAdapter(albumListData);

        binding.setError(error);
        albumList.setLayoutManager(new LinearLayoutManager(this));
        albumList.setAdapter(albumAdapter);

        handler = new AppleMusicRequestHandler(error);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadScreen.setVisibility(View.VISIBLE);

        if(handler.isConnection()){
            handler.getAlbumData(albumAdapter, albumListData, this);

            if(error.getIsVisible()){
                error.clearError();
            }
        } else if(!error.getIsVisible()){
            error.setError("Could not connect to iTunes' RSS feed");
        }

        loadScreen.setVisibility(View.GONE);
    }
}
