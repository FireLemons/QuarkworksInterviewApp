package com.example.fly_s_y.applemusicalbumviewer;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fly_s_y.JSON.AppleMusicRequestHandler;
import com.example.fly_s_y.applemusicalbumviewer.databinding.ActivityMusicListViewBinding;

public class MusicListView extends AppCompatActivity {

    private AppleMusicRequestHandler handler;
    private View loadScreen;
    private ErrorDisplay error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        handler = new AppleMusicRequestHandler(error);
    }

    @Override
    protected void onResume() {
        loadScreen.setVisibility(View.VISIBLE);
        super.onResume();

        if(handler.isConnection() && error.getIsVisible()){
            error.setIsVisible(false);
        } else if(!handler.isConnection() && !error.getIsVisible()){
            error.setErrorMessage("Could not connect to iTunes' RSS feed");
            error.setIsVisible(true);
        }
        loadScreen.setVisibility(View.GONE);
    }

    private void initView(){
        ActivityMusicListViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_music_list_view);

        loadScreen = findViewById(R.id.loadScreen);
        error = new ErrorDisplay();

        binding.setError(error);
    }
}
