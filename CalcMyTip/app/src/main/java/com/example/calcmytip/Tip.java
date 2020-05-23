package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Tip extends AppCompatActivity {

   TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        tvTotal = findViewById(R.id.textViewTotal);
        tvTotal.setText(getIntent().getStringExtra(Bill.KEY_BILL));

    }

    public void onClickPay(View view) {
        Intent intent = new Intent(this, Video.class);
        intent.putExtra(MainActivity.KEY_RESTAURANTNAME,getIntent().getStringExtra(MainActivity.KEY_RESTAURANTNAME));
        startActivity(intent);
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
