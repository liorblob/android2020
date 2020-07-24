package com.example.stackoverflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_SEARCH = "com.example.stackoverflow.SEARCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search(View v){
        Intent intent = new Intent(this, ResultsActivity.class);
        EditText editText = findViewById(R.id.search_text);
        String searchText = editText.getText().toString();
        intent.putExtra(EXTRA_SEARCH, searchText);
        startActivity(intent);
    }
}
