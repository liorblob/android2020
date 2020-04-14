package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Rating2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating2);
    }

    public void ThirdButton(View view) {
        Intent intent = new Intent(this, Rating3.class);
        startActivity(intent);
    }
}
