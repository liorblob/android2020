package com.example.calcmytip;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

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
            nextActivity = new Intent(this, MainActivity.class);
        }
        else if (id == R.id.rating1) {
            nextActivity = new Intent(this, Rating1.class);
        }
        else if (id == R.id.rating2) {
            nextActivity = new Intent(this, Rating2.class);
        }
        else if (id == R.id.rating3)
        {
            nextActivity = new Intent(this, Rating3.class);
        }
        else
        {
            nextActivity = new Intent(this, LocationActivity.class);
        }

        startActivity(nextActivity);

        return super.onOptionsItemSelected(item);

    }
}
