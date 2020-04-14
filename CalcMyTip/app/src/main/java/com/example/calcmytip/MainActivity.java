package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_RESTAURANTNAME = "restaurantNameKey";
    EditText restoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restoName = findViewById(R.id.RestoName);
    }

    public void onClickNext(View view) {

        Intent intent = new Intent(this, Rating1.class);
        intent.putExtra(KEY_RESTAURANTNAME,restoName.getText().toString());
        startActivity(intent);
    }


}
