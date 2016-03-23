package com.nscc.w0281673.vidplayer;


import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class VideoActivity extends Activity {
    private DBHelper vidDB;
    EditText rating;
    VideoView mVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        Bundle b = getIntent().getExtras();
        final int x = b.getInt("searchId");
        int y = b.getInt("searchId");
        rating = (EditText) findViewById(R.id.ratingField);
        Button ratingButton, deleteButton;

        vidDB = new DBHelper(this);
        ArrayList<String> arrList = vidDB.getAllTrailers();

        ratingButton = (Button) findViewById(R.id.ratingButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        if(ratingButton != null)
        {
            ratingButton.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {

                    if (rating.getText().toString() != null)
                        vidDB.addRating(x, Integer.parseInt(rating.getText().toString()));
                    else
                        vidDB.addRating(x, 0);
                }
            });
        }
        if(deleteButton != null)
        {
            deleteButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    vidDB.deleteVideo(x);
                }
            });
        }

        String videoUrl= "android.resource://" + getPackageName() + "/" + getResources().getIdentifier(arrList.get(b.getInt("searchId")-1), "raw", getPackageName());

        mVideoView = (VideoView) findViewById(R.id.videoView);
        mVideoView.setVideoPath(videoUrl);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);

        if(savedInstanceState!=null)
        {
            int currentPosition = savedInstanceState.getInt("current position");
            mVideoView.seekTo(currentPosition);
        }
        mVideoView.start();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("current position", mVideoView.getCurrentPosition());
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
