package com.nscc.w0281673.vidplayer;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AddVideoActivity extends Activity {
    private DBHelper vidDB;
    private TextView name;
    private TextView description;
    private TextView trailer;
    private TextView thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);
        Button addVideoButton = (Button) findViewById(R.id.addVideoButton);
        name = (TextView) findViewById(R.id.enterName);
        description = (TextView) findViewById(R.id.enterDescription);
        trailer = (TextView) findViewById(R.id.enterTrailer);
        thumbnail = (TextView) findViewById(R.id.enterThumbnail);
        vidDB = new DBHelper(this);
        addVideoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vidDB.insertVideo(name.getText().toString(), description.getText().toString(), trailer.getText().toString(), thumbnail.getText().toString(), 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_video, menu);
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
