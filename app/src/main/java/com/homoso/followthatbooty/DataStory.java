package com.homoso.followthatbooty;

import android.graphics.drawable.Drawable;

import java.net.URL;

/**
 * Created by rohan on 23/3/16.
 */
public class DataStory {

    public String description, id, verb, db, type, title;
    public Drawable cover, profile;
    public boolean liked;
    public int likes, comments;
    public String imageUrl, storyUrl;

    public DataStory(){
        this.title= null;
        this.description= null;
        this.verb= null;
        this.cover= null;
        this.profile= null;
        this.db= null;
        this.id= null;
        this.imageUrl= null;
        this.storyUrl= null;
        this.type= null;
        this.liked= false;
        this.likes = 0;
        this.comments= 0;
    }

}
