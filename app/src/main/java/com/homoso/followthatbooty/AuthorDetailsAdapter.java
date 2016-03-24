package com.homoso.followthatbooty;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rohan on 23/3/16.
 */
public class AuthorDetailsAdapter extends RecyclerView.Adapter<AuthorDetailsAdapter.ViewHolder> {
    private ArrayList<DataStory> stories;
    private ArrayList<DataAuthor> authors;
    Context context;
    RelativeLayout layout;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, story, author, totalFollowers;
        ImageView cover, profile, follow;
        LinearLayout elementLayout;
        RelativeLayout followLayout;

        public ViewHolder(View v) {
            super(v);
            title= (TextView) v.findViewById(R.id.tvStoryTitle);
            story= (TextView) v.findViewById(R.id.tvStoryContent);
            author= (TextView) v.findViewById(R.id.tvStoryAuthor);
            totalFollowers= (TextView) v.findViewById(R.id.tvStoryTotalFollowers);
            cover= (ImageView) v.findViewById(R.id.ivStoryCover);
            profile= (ImageView) v.findViewById(R.id.ivStoryProfile);
            follow= (ImageView) v.findViewById(R.id.ivStoryFollow);
            elementLayout= (LinearLayout) v.findViewById(R.id.llStory);
            followLayout= (RelativeLayout) v.findViewById(R.id.rlStoryFollow);
        }
    }

    public AuthorDetailsAdapter(ArrayList<DataStory> stories, ArrayList<DataAuthor> authors, Context context, RelativeLayout layout) {
        this.stories= stories;
        this.authors= authors;
        this.layout= layout;
        this.context= context;
    }

    @Override
    public AuthorDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_story, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(stories.get(position).title);
        holder.story.setText(stories.get(position).description);
        String db= stories.get(position).db;
        for(int i=0; i<authors.size(); i++){
            if(authors.get(i).id.equals(db)){
                holder.author.setText(authors.get(i).name);
                holder.totalFollowers.setText(authors.get(i).followers + "");
            }
        }

        if(stories.get(position).cover!= null)
            holder.cover.setBackgroundDrawable(stories.get(position).cover);
        if(stories.get(position).profile!= null)
            holder.profile.setBackgroundDrawable(stories.get(position).profile);

        if(stories.get(position).liked) {
            holder.followLayout.setBackgroundDrawable(tintDrawable(R.drawable.custom_follow_button, R.color.custom_primary_color));
            holder.follow.setBackgroundDrawable(tintDrawable(R.drawable.icon_tick, R.color.blue));
        }
        else {
            holder.followLayout.setBackgroundDrawable(tintDrawable(R.drawable.custom_follow_button, R.color.blue));
            holder.follow.setBackgroundDrawable(tintDrawable(R.drawable.icon_add_white, R.color.custom_primary_color));
        }

        holder.followLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!stories.get(position).liked) {
                    holder.followLayout.setBackgroundDrawable(tintDrawable(R.drawable.custom_follow_button, R.color.custom_primary_color));
                    holder.follow.setBackgroundDrawable(tintDrawable(R.drawable.icon_tick, R.color.blue));
                }
                else {
                    holder.followLayout.setBackgroundDrawable(tintDrawable(R.drawable.custom_follow_button, R.color.blue));
                    holder.follow.setBackgroundDrawable(tintDrawable(R.drawable.icon_add_white, R.color.custom_primary_color));
                }

                for(int i=0; i<stories.size(); i++){
                    if(stories.get(i).db.equals(stories.get(position).db)){
                        stories.get(i).liked= !stories.get(i).liked;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    void showSnackBar(String message){
        if(layout!= null)
            Snackbar.make(layout, message, Snackbar.LENGTH_SHORT).show();
    }

    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private Drawable tintDrawable(int drawableId, int colorId){
        Drawable drawable = context.getResources().getDrawable(drawableId);
        int primaryColor =  context.getResources().getColor(colorId);
        drawable.setColorFilter(primaryColor, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

}