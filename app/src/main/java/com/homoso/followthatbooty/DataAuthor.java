package com.homoso.followthatbooty;

import java.net.URI;
import java.net.URL;

/**
 * Created by raj on 23/3/16.
 */
public class DataAuthor {

    public String about, id, name, handle, createdOn;
    public int followers, following;
    public String imageUrl, profileUrl;
    public boolean isFollowing;

    public DataAuthor(){
        about= null;
        id= null;
        name= null;
        handle= null;
        createdOn= null;
        imageUrl= null;
        profileUrl= null;
        isFollowing= false;
        followers= -1;
        following= -1;
    }

}
