package com.homoso.followthatbooty;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryDetail extends AppCompatActivity {

    ImageView storyImage;
    TextView storyDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        storyImage= (ImageView)findViewById(R.id.story_image);
        storyDetail=(TextView)findViewById(R.id.story_detail);
        Bitmap bitmap = this.getIntent().getParcelableExtra("storyImage");
        storyImage.setImageBitmap(bitmap);
        storyDetail.setText(this.getIntent().getExtras().getString("storyDetail"));
    }

}
