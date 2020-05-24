package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Bill extends AppCompatActivity {

    public static final String KEY_BILL = "billKey";
    public static final String KEY_TOPAY = "toPayKey";

    int tip;
    TextView textTip;
    EditText editTextBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        textTip = findViewById(R.id.textViewTip);
        editTextBill = findViewById(R.id.textEditBill);

        //Retrieving all 3 ratings and calculating Tip Percentage
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        tip = (prefs.getInt(Rating1.KEY_TIP,0) + prefs.getInt(Rating2.KEY_TIP,0) + prefs.getInt(Rating3.KEY_TIP,0));

        //Retrieving Bill before tip and presenting to the user
        String str = prefs.getString(KEY_TOPAY,"");
        editTextBill.setText(str);
        textTip.setText(Integer.toString(tip));

       //Log State
        Log.i("Shared Preferences:", "Setting To pay: "+ str);
    }

    public void onCalculateBill(View view) {

        String billStr = editTextBill.getText().toString();
        int bill = Integer.parseInt(billStr);


        int total = (bill*(100+tip))/100;

        //Passing total bill value
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_BILL, ""+total);
        editor.commit();
        startActivity(new Intent(this, Tip.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Save As last Activity
        Dispatcher.saveActivity(this);

        //Save Activity's data
        SharedPreferences pref = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_TOPAY,editTextBill.getText().toString());
        editor.commit();
        Log.i("Shared Preferences:", "Saving To pay: "+ editTextBill.getText().toString());
    }
}
