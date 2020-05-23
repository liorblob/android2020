package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class Rating3 extends RatingBase {

    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating3);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void onClickNext(View view){
        Intent intent = new Intent(this,  Bill.class );
        intent.putExtra(RatingBase.KEY_TIP,calculateRating(baseTip));
        intent.putExtra(MainActivity.KEY_RESTAURANTNAME, restName);
        startActivity(intent);
    }

    @Override
    protected String calculateRating(String base){
        int iBase = Integer.parseInt(base);
        int rank = 0;
        switch(radioGroup.getCheckedRadioButtonId()){
            case R.id.radioButton1:
                rank = 6;
                break;
            case R.id.radioButton2:
                rank = 4;
                break;
            case R.id.radioButton3:
                rank = 2;
                break;
        }
        return (String.valueOf(iBase + rank));
    }


}
