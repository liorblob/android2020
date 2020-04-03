package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText bill;
    EditText tip;
    TextView total;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bill = (EditText)findViewById(R.id.editText2);
        tip = (EditText)findViewById(R.id.editText3);
        total = (TextView) findViewById(R.id.textView3);
        mediaPlayer = MediaPlayer.create(this, R.raw.cash_sound);

    }

    public void calcTip(View v){
        String billStr = bill.getText().toString();
        String tipString = tip.getText().toString();

        double totalBill = Integer.parseInt(billStr) * (1+Integer.parseInt(tipString)*0.01);
        int totalBillRound = (int)(totalBill);
        total.setText(Integer.toString(totalBillRound));

        mediaPlayer.start();
    }
}
