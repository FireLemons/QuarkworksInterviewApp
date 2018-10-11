package com.example.fly_s_y.applemusicalbumviewer;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fly_s_y.applemusicalbumviewer.databinding.ActivityMusicListViewBinding;

public class MusicListView extends AppCompatActivity {

    private View loadScreen;
    private ErrorDisplay error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    protected void onResume() {
        loadScreen.setVisibility(View.VISIBLE);
        super.onResume();
        loadScreen.setVisibility(View.GONE);
    }

    private void initView(){
        ActivityMusicListViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_music_list_view);

        loadScreen = findViewById(R.id.loadScreen);
        error = new ErrorDisplay("Yeet");

        binding.setError(error);
    }
}
