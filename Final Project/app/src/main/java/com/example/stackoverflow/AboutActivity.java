package com.example.stackoverflow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.stackoverflow.services.DispatcherService;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Save As last Activity
        DispatcherService.saveActivity(this);
    }
}