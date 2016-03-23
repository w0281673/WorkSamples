package com.nscc.w0281673.vidplayer;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.database.sqlite.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private DBHelper vidDB;
    private ListView vidListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vidDB = new DBHelper(this);

        //vidDB.alterTable();
       // boolean ins = vidDB.insertVideo("Captain America - Civil War", "CAPTAIN AMERICA", "cacwtrailer", "tn1");
        //ins = vidDB.insertVideo("Star Wars - The Force Awakens", "STAR WARS", "swfatrailer", "tn2");
        //ins = vidDB.insertVideo("Warcraft", "WORLD OF WARCRAFT", "wctrailer", "tn3");

        ArrayList nameList = vidDB.getAllVideos();
        ArrayList descList = vidDB.getAllDescriptions();
        ArrayList trailList = vidDB.getAllTrailers();
        ArrayList thumbList = vidDB.getAllThumbnails();
        ArrayList ratingList = vidDB.getAllRatings();

        nameList.add("Add New Trailer");
        descList.add("Add New Trailer");
        thumbList.add("tn1");
        VidListAdapter vidAdapter = new VidListAdapter(this, nameList, descList, thumbList, ratingList);


        vidListView = (ListView) findViewById(R.id.videoListView);
        vidListView.setAdapter(vidAdapter);
        vidListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adv, View view, int i, long l){
                int searchId = i + 1;
                Bundle b = new Bundle();

                LinearLayout ll = (LinearLayout)findViewById(R.id.outerLayout);
                LinearLayout lll = (LinearLayout)ll.getChildAt(1);
                TextView tv = (TextView)lll.findViewById(R.id.movie_name);
                vidListView.getItemAtPosition(vidListView.getCount()-1);
                if(searchId==vidListView.getCount())
                {
                    Intent intent = new Intent(getApplicationContext(), AddVideoActivity.class);
                    startActivity(intent);
                }
                else
                {
                    b.putInt("searchId", searchId);
                    Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                    intent.putExtras(b);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
