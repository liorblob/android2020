package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class Rating2 extends AppCompatActivity {

    public static final String KEY_TIP = "rateKey2";
    public static final String[] RATES = new String[]{"First","Second","Third"};
    CheckBox[] ratings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating2);
        ratings = new CheckBox[]{
                (CheckBox)findViewById(R.id.checkBox1),
                (CheckBox)findViewById(R.id.checkBox2),
                (CheckBox)findViewById(R.id.checkBox3)
        };
        SharedPreferences pref = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
        for (int i =0 ; i <3 ; i++) {
            ((CheckBox)ratings[i]).setChecked(pref.getBoolean(RATES[i],false));
            Log.i("Shared Preferences:", "Setting Checkbox"+(i+1)+"="+pref.getBoolean(RATES[i],false));
        }

    }

    public void onClickNext(View view){
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_TIP,calculateRating());
        editor.commit();
        startActivity(new Intent(this,  Rating3.class ));
    }

    private int calculateRating(){

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
        return rank;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Dispatcher.saveActivity(this);
        SharedPreferences pref = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        for (int i =0 ; i <3 ; i++) {
            editor.putBoolean(RATES[i],((CheckBox)ratings[i]).isChecked());
            Log.i("Shared Preferences:", "Saving Checkbox"+(i+1)+"="+((CheckBox)ratings[i]).isChecked());
        }
        editor.commit();
    }
}
