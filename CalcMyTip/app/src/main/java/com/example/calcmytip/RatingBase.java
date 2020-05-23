package com.example.calcmytip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public abstract class RatingBase extends AppCompatActivity {

    public static final String KEY_TIP = "rateKey";

    protected String baseTip;
    protected String restName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_rating1);

        baseTip = getIntent().getStringExtra(RatingBase.KEY_TIP);
        if (baseTip == null){
            baseTip = "0";
        }
        restName = getIntent().getStringExtra(MainActivity.KEY_RESTAURANTNAME);
    }


    protected abstract String calculateRating(String base);


}
