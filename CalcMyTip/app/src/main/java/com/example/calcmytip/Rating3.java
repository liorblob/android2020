package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        //retrieving saved data
        int id = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE).getInt(KEY_RADIO, 0);
        if (id > 0){
            RadioButton radio = findViewById(id);
            radio.setChecked(true);

            //log
            String fullName = getResources().getResourceName(id);
            String name = fullName.substring(fullName.lastIndexOf("/") + 1);
            Log.i("Shared Preferences:", "Setting Radio button id: "+ name);
        }

    }

    public void onClickNext(View view){
        saveData();
        startActivity(new Intent(this,  Bill.class ));
    }

    private void saveData() {
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_TIP,calculateRating());
        editor.commit();
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

    @Override
    protected void onPause() {
        super.onPause();
        //Save As last Activity
        Dispatcher.saveActivity(this);

        //Save Activity's data
        SharedPreferences pref = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(KEY_RADIO,radioGroup.getCheckedRadioButtonId());
        editor.commit();

        //log
        RadioButton radio = findViewById(radioGroup.getCheckedRadioButtonId());
        String fullName = getResources().getResourceName(radio.getId());
        String name = fullName.substring(fullName.lastIndexOf("/") + 1);
        Log.i("Shared Preferences:", "Saving Radio button id: "+ name);
    }

}
