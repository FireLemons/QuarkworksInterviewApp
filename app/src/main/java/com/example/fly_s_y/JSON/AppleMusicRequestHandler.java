package com.example.fly_s_y.JSON;

import com.example.fly_s_y.applemusicalbumviewer.ErrorDisplay;

public class AppleMusicRequestHandler extends JSONFetcher{
    public AppleMusicRequestHandler(ErrorDisplay errorDisplay){
        super("rss.itunes.apple.com", errorDisplay);
    }

    public void getAlbumData(){

    }
}
