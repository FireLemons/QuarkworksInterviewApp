package com.example.fly_s_y.applemusicalbumviewer;

/**
 * A music genre
 */
public class Genre {
    private String name, link;

    public Genre(String name, String link){
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}
