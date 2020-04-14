package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText RestoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestoName = findViewById(R.id.RestoName);
    }

    public void firstButton(View view) {
        Intent intent = new Intent(this, Rating1.class);
        startActivity(intent);
    }
}
