package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        startActivity(nextActivity);

        return super.onOptionsItemSelected(item);

    }

}
