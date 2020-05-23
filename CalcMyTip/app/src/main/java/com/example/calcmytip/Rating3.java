package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Rating3 extends AppCompatActivity {

    public static final String KEY_TIP = "rateKey3";
    public static final String KEY_RADIO = "radioKey";
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating3);
        radioGroup = findViewById(R.id.radioGroup);
        int id = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE).getInt(KEY_RADIO, 0);
        if (id != 0){
            RadioButton radio = findViewById(id);
            radio.setChecked(true);
            Log.i("Shared Preferences:", "Setting Radio button id: "+ radio.getId());
        }

    }

    public void onClickNext(View view){
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_TIP,calculateRating());
        editor.commit();
        startActivity(new Intent(this,  Bill.class ));
    }

    private int calculateRating(){

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
        return rank;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Dispatcher.saveActivity(this);
        SharedPreferences pref = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(KEY_RADIO,radioGroup.getCheckedRadioButtonId());
        editor.commit();
        RadioButton radio = findViewById(radioGroup.getCheckedRadioButtonId());
        Log.i("Shared Preferences:", "Saving Radio button id: "+ radio.getId());
    }

}
