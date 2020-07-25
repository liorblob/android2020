package com.example.stackoverflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.stackoverflow.adapter.HistoryAdapter;
import com.example.stackoverflow.model.Search;
import com.example.stackoverflow.services.DBService;
import com.example.stackoverflow.services.DispatcherService;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private DBService dbService;
    private List<Search> searches;
    private RecyclerView historyRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbService = new DBService(this);
        searches = dbService.getSearches();
        historyRecycler = findViewById(R.id.historyRecyclerView);

        HistoryAdapter adapter = new HistoryAdapter(searches);
        historyRecycler.setAdapter(adapter);
        historyRecycler.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));

    }


    @Override
    protected void onPause() {
        super.onPause();

        //Save As last Activity
        DispatcherService.saveActivity(this);
    }

    public void gohome(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}