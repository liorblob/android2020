package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Rating1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating1);
    }

    public void SecondButton(View view) {
        Intent intent = new Intent(this, Rating2.class);
        startActivity(intent);
    }
}
