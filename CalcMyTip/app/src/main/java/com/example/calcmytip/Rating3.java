package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Rating3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating3);
    }

    public void ForthButton(View view) {
        Intent intent = new Intent(this, Bill.class);
        startActivity(intent);
    }
}
