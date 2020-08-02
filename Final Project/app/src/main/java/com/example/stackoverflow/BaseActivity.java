package com.example.stackoverflow;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                ActionBar.DISPLAY_SHOW_TITLE  | ActionBar.DISPLAY_USE_LOGO);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
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
        } else if (id == R.id.history_ac) {
            nextActivity = new Intent(this, HistoryActivity.class);
        } else if (id == R.id.about_ac) {
            nextActivity = new Intent(this, AboutActivity.class);
        } else {
            return super.onOptionsItemSelected(item);
        }

        startActivity(nextActivity);

        return super.onOptionsItemSelected(item);

    }
}
