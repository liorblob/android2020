package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Bill extends AppCompatActivity {

    public static final String KEY_BILL = "billKey";
    String tipStr;
    TextView textTip;
    EditText editTextBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        textTip = findViewById(R.id.textViewTip);
        editTextBill = findViewById(R.id.textEditBill);
        tipStr = getIntent().getStringExtra(RatingBase.KEY_TIP);
        textTip.setText(tipStr);
    }

    public void onCalculateBill(View view) {

        String billStr = editTextBill.getText().toString();
        int bill = Integer.parseInt(billStr);
        int tip = Integer.parseInt(tipStr);

        int total = (bill*(100+tip))/100;

        Intent intent = new Intent(this, Tip.class);
        intent.putExtra(MainActivity.KEY_RESTAURANTNAME,getIntent().getStringExtra(MainActivity.KEY_RESTAURANTNAME));
        intent.putExtra( KEY_BILL, ""+total);
        startActivity(intent);
    }
}
