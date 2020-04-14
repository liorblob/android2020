package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Tip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
    }

    public void FinalButton(View view) {
        Intent intent = new Intent(this, Video.class);
        startActivity(intent);
    }
}
