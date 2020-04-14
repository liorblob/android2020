package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Bill extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        videoView=findViewById(R.id.videoView);

        String uriStr="android.resource://"+ getPackageName()+"/"+R.raw.tippingaroundtheworld;
        Uri uri= Uri.parse(uriStr);
        videoView.setVideoURI(uri);

        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();

        videoView.start();

    }
}
