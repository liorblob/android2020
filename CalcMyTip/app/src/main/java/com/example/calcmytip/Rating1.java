package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

public class Rating1 extends AppCompatActivity {

    public static final String KEY_TIP = "rateKey1";
    public static final String KEY_BAR = "rbKey";
    RatingBar rbWaiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating1);

        rbWaiter = findViewById(R.id.waiterRating);
        float f = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE).getFloat(KEY_BAR, 0f);
        rbWaiter.setRating(f);
        Log.i("Shared Preferences:", "get RatingBar rate: "+f);
    }

    public void onClickNext(View view){
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_TIP,calculateRating());
        editor.commit();
        startActivity(new Intent(this,  Rating2.class ));
    }


    private int calculateRating(){

        return (int)rbWaiter.getRating();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Dispatcher.saveActivity(this);
        SharedPreferences pref = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(KEY_BAR,rbWaiter.getRating());
        editor.commit();
        Log.i("Shared Preferences:", "Saving RatingBar rate: "+rbWaiter.getRating());
    }
}
