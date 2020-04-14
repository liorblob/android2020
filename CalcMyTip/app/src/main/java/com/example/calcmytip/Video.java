package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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
        tvRestName.setText(getIntent().getStringExtra(MainActivity.KEY_RESTAURANTNAME));

        MediaPlayer.create(this,R.raw.cashsound).start();
        videoView=findViewById(R.id.videoView);

        String uriStr="android.resource://"+ getPackageName()+"/"+R.raw.creditcard;
        Uri uri= Uri.parse(uriStr);
        videoView.setVideoURI(uri);

        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();

        videoView.start();
    }
}