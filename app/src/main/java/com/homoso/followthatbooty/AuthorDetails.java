package com.homoso.followthatbooty;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by rohan on 23/3/16.
 */
public class AuthorDetails extends AppCompatActivity {

    private Toolbar toolbar;
    RelativeLayout layout;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<DataStory> stories;
    ArrayList<DataAuthor> authors;
    String LOG_TAG= "AuthorDetails Logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_details);

        init();
        setToolbar();
    }

    /*
    **Initializations.
    * */

    private void init(){
        layout= (RelativeLayout) findViewById(R.id.rlAuthorDetails);
        recyclerView = (RecyclerView) findViewById(R.id.rvAuthorDetails);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        stories= new ArrayList<>();
        authors= new ArrayList<>();

        String s= loadJsonFromAssets();
        if(s!= null){
            try {
                JSONArray jsonArray= new JSONArray(s);
                String authorName= getIntent().getStringExtra("author");

                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject object= (JSONObject) jsonArray.get(i);
                    if(object.has("about")) {
                        if (object.optString("username").equals(authorName)) {
                            DataAuthor author = new DataAuthor();
                            author.name = object.optString("username");
                            author.about = object.optString("about");
                            author.id = object.optString("id");
                            author.followers = object.optInt("followers");
                            author.following = object.optInt("following");
                            author.handle = object.optString("handle");
                            author.createdOn = object.optString("createdOn");
                            author.imageUrl = object.optString("image");
                            author.profileUrl = object.optString("url");
                            author.isFollowing = object.optBoolean("is_following");
                            authors.add(author);
                            break;
                        }
                    }
                }

                String db= authors.get(0).id;

                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject object= (JSONObject) jsonArray.get(i);
                    if(object.has("description")) {
                        if(object.optString("db").equals(db)) {
                            DataStory storyData = new DataStory();
                            storyData.description = object.optString("description");
                            storyData.verb = object.optString("verb");
                            storyData.id = object.optString("id");
                            storyData.likes = object.optInt("likes_count");
                            storyData.comments = object.optInt("comment_count");
                            storyData.db = object.optString("db");
                            storyData.type = object.optString("type");
                            storyData.imageUrl = object.optString("image");
                            storyData.storyUrl = object.optString("si");
                            storyData.title = object.optString("title");
                            storyData.liked = object.optBoolean("like_flag");
                            stories.add(storyData);
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Log.d(LOG_TAG, "null");
            showToast("null");
        }

        adapter = new AuthorDetailsAdapter(stories, authors, AuthorDetails.this, layout);
        recyclerView.setAdapter(adapter);
    }

    private void setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.tbAuthorDetails);
        toolbar.setTitle("Author Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    **Display Assets..
    * */

    private void showSnackBar(String message){
        if(message!=null)
            Snackbar.make(layout, message, Snackbar.LENGTH_SHORT).show();
    }

    public void showToast(String message){
        if(message!= null){
            Toast.makeText(AuthorDetails.this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public String loadJsonFromAssets(){
        String json= null;
        try {
            InputStream is= getAssets().open("data.json");
            int size= is.available();
            byte[] buffer= new byte[size];
            is.read(buffer);
            is.close();
            json= new String(buffer, "UTF-8");
        } catch (IOException e) {
            showSnackBar("Exception...");
            e.printStackTrace();
        }

        return json;
    }

    /*
    **Overriding events.
    * */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}