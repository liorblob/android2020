package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Rating2 extends RatingBase {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating2);


    }

    public void onClickNext(View view){
        Intent intent = new Intent(this,  Rating3.class );
        intent.putExtra(RatingBase.KEY_TIP,calculateRating(baseTip));
        intent.putExtra(MainActivity.KEY_RESTAURANTNAME, restName);
        startActivity(intent);
    }

    @Override
    protected String calculateRating(String base){
        int iBase = Integer.parseInt(base);
        int rank = 0;
        if (((CheckBox)findViewById(R.id.checkBox1)).isChecked()){
            rank += 2;
        }
        if (((CheckBox)findViewById(R.id.checkBox2)).isChecked()){
            rank += 2;
        }
        if (((CheckBox)findViewById(R.id.checkBox3)).isChecked()){
            rank += 2;
        }
        return (String.valueOf(iBase + rank));
    }
}