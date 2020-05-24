package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    VideoView videoView;
    TextView tvRestName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        tvRestName = findViewById(R.id.textViewRestName);
        tvRestName.setText(getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE).getString(MainActivity.KEY_RESTAURANT,"CoffeBar"));

        MediaPlayer.create(this,R.raw.cashsound).start();
        videoView=findViewById(R.id.videoView);

        String uriStr="android.resource://"+ getPackageName()+"/"+R.raw.creditcard;
        Uri uri= Uri.parse(uriStr);
        videoView.setVideoURI(uri);

        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();

        videoView.start();

        //clear Saved Activity
        SharedPreferences prefs = getSharedPreferences(Dispatcher.DISPATCH, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(Dispatcher.LAST_ACTIVITY);
        editor.commit();

        //clear Saved prefs
        prefs = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
        editor = prefs.edit();
        editor.clear();
        editor.commit();

        //log
        Log.i("Shared Preferences:", "Last activity, Removing LAST_ACTIVITY Key");
        Log.i("Shared Preferences:", "Last activity, Clearing PREFS");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent nextActivity;

        int id = item.getItemId();
        if (id == R.id.home) {
            nextActivity = new Intent(this,MainActivity.class);
        }
        else if (id == R.id.rating1) {
            nextActivity = new Intent(this,Rating1.class);
        }
        else if (id == R.id.rating2) {
            nextActivity = new Intent(this,Rating2.class);
        }
        else
        {
            nextActivity = new Intent(this,Rating3.class);
        }

        startActivity(nextActivity);

        return super.onOptionsItemSelected(item);

    }

}
