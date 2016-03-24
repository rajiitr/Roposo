package com.homoso.followthatbooty;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class FragmentStory extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RelativeLayout layout;

    ArrayList<DataStory> stories;
    ArrayList<DataAuthor> authors;
    String LOG_TAG= "FragmentStory Logs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, container, false);
        init(view);
        return view;
    }

    /*
    **Logic Implementation.
    * */

    /*
    **Database Interface.
    * */

    /*
    **Initializations.
    * */

    private void init(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.rvStory);
        layout= (RelativeLayout) view.findViewById(R.id.rlStory);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        stories= new ArrayList<>();
        authors= new ArrayList<>();

        String s= loadJsonFromAssets();
        if(s!= null){
            try {
                JSONArray jsonArray= new JSONArray(s);

                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject object= (JSONObject) jsonArray.get(i);
                    if(object.has("about")) {
                        DataAuthor author = new DataAuthor();
                        author.name= object.optString("username");
                        author.about= object.optString("about");
                        author.id= object.optString("id");
                        author.followers= object.optInt("followers");
                        author.following= object.optInt("following");
                        author.handle= object.optString("handle");
                        author.createdOn= object.optString("createdOn");
                        author.imageUrl= object.optString("image");
                        author.profileUrl= object.optString("url");
                        author.isFollowing= object.optBoolean("is_following");
                        authors.add(author);
                    }
                }

                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject object= (JSONObject) jsonArray.get(i);
                    if(object.has("description")) {
                        DataStory storyData = new DataStory();
                        storyData.description= object.optString("description");
                        storyData.verb= object.optString("verb");
                        storyData.id= object.optString("id");
                        storyData.likes= object.optInt("likes_count");
                        storyData.comments= object.optInt("comment_count");
                        storyData.db= object.optString("db");
                        storyData.type= object.optString("type");
                        storyData.imageUrl= object.optString("image");
                        storyData.storyUrl= object.optString("si");
                        storyData.title= object.optString("title");
                        storyData.liked= object.optBoolean("like_flag");
                        stories.add(storyData);
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

        adapter = new StoryAdapter(stories, authors, getActivity(), layout);
        recyclerView.setAdapter(adapter);
    }

    public String loadJsonFromAssets(){
        String json= null;
        try {
            InputStream is= getActivity().getAssets().open("data.json");
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
    **Display Assets..{
}

    * */

    public void showSnackBar(String message){
        Snackbar.make(layout, message, Snackbar.LENGTH_SHORT).show();
    }

    public void showToast(String message){
        if(message!= null){
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /*
    **Overriding events.
    * */

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
    }

}
