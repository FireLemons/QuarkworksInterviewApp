package com.example.fly_s_y.applemusicalbumviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MusicListView extends AppCompatActivity {

    private View loadScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list_view);

        String[] test = "['1']['2'].three.four['5']".split("[\\[\\]'\".]+");

        for(String s : test){
            Log.d("Token", s);
        }

        loadScreen  = findViewById(R.id.loadScreen);
    }

    @Override
    protected void onResume() {
        loadScreen.setVisibility(View.VISIBLE);
        super.onResume();
        loadScreen.setVisibility(View.GONE);
    }
}
