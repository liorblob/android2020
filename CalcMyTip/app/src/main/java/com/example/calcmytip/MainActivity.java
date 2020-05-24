package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS = "prefsKey";
    public static final String KEY_RESTAURANT = "restaurantNameKey";
    EditText restoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restoName = findViewById(R.id.RestoName);

        //retrieving saved data
        String str = getSharedPreferences(PREFS,MODE_PRIVATE).getString(KEY_RESTAURANT, "");
        restoName.setText(str);

        //log
        Log.i("Shared Preferences:", "get restaurant name: "+str);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Save Activity's data
        SharedPreferences pref = getSharedPreferences(PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_RESTAURANT,restoName.getText().toString());
        editor.commit();

        //log
        Log.i("Shared Preferences:", "Saving restaurant name: "+restoName.getText().toString());
    }

    public void onClickNext(View view) {

        startActivity(new Intent(this, Rating1.class));
    }


}
