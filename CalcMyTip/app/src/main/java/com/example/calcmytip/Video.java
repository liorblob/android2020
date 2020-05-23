package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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
        Log.i("Shared Preferences:", "Last activity, Removing LAST_ACTIVITY Key");

        //clear Saved prefs
        prefs = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
        editor = prefs.edit();
        editor.clear();
        editor.commit();
        Log.i("Shared Preferences:", "Last activity, Clearing PREFS");
    }


}
