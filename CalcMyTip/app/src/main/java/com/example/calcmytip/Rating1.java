package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class Rating1 extends RatingBase {


    RatingBar rbWaiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating1);

        rbWaiter = findViewById(R.id.waiterRating);

    }

    public void onClickNext(View view){
        Intent intent = new Intent(this,  Rating2.class );
        intent.putExtra(RatingBase.KEY_TIP,calculateRating(baseTip));
        intent.putExtra(MainActivity.KEY_RESTAURANTNAME, restName);
        startActivity(intent);
    }

    @Override
    protected String calculateRating(String base){
        int iBase = Integer.parseInt(base);

        return (String.valueOf(iBase + (int)rbWaiter.getRating()));
    }
}
