package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Tip extends BaseActivity {

   TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        tvTotal = findViewById(R.id.textViewTotal);
        tvTotal.setText(getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE).getString(Bill.KEY_BILL,"0"));

    }

    public void onClickPay(View view) {

        startActivity(new Intent(this, Video.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Save As last Activity
        Dispatcher.saveActivity(this);

    }
}
