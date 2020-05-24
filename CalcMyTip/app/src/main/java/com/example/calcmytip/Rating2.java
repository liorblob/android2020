package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        //retrieving saved data
        ratings = new CheckBox[]{
                (CheckBox)findViewById(R.id.checkBox1),
                (CheckBox)findViewById(R.id.checkBox2),
                (CheckBox)findViewById(R.id.checkBox3)
        };
        SharedPreferences pref = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
        for (int i =0 ; i <3 ; i++) {
            ((CheckBox)ratings[i]).setChecked(pref.getBoolean(RATES[i],false));

            //log
            Log.i("Shared Preferences:", "Setting Checkbox"+(i+1)+"="+pref.getBoolean(RATES[i],false));
        }

    }

    public void onClickNext(View view){
        saveData();
        startActivity(new Intent(this,  Rating3.class ));
    }

    private void saveData() {
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_TIP,calculateRating());
        editor.commit();
    }

    private int calculateRating(){

        int rank = 0;
        for (CheckBox cb: ratings) {
            if(cb.isChecked()){
                rank += 2;
            }
        }

        return rank;
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Save As last Activity
        Dispatcher.saveActivity(this);

        //Save Activity's data
        SharedPreferences pref = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        for (int i =0 ; i <3 ; i++) {
            editor.putBoolean(RATES[i],((CheckBox)ratings[i]).isChecked());

            //log
            Log.i("Shared Preferences:", "Saving Checkbox"+(i+1)+"="+((CheckBox)ratings[i]).isChecked());
        }
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent nextActivity;

        int id = item.getItemId();
        if (id == R.id.home) {
            nextActivity = new Intent(this,MainActivity.class);
        }
        else if (id == R.id.rating1) {
            nextActivity = new Intent(this,Rating1.class);
        }
        else if (id == R.id.rating2) {
            nextActivity = new Intent(this,Rating2.class);
        }
        else
        {
            nextActivity = new Intent(this,Rating3.class);
        }
        saveData();
        startActivity(nextActivity);

        return super.onOptionsItemSelected(item);

    }
}
