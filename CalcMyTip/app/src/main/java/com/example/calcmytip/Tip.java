package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
}
