package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
