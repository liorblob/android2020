package com.example.stackoverflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.stackoverflow.adapter.PostAdapter;
import com.example.stackoverflow.model.Item;
import com.example.stackoverflow.model.StackOFResult;
import com.example.stackoverflow.network.GetStackOFDataService;
import com.example.stackoverflow.network.RetrofitInstance;
import com.example.stackoverflow.services.DispatcherService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsActivity extends AppCompatActivity {
    private GetStackOFDataService stackOFService;
    List<Item> posts;
    RecyclerView rvPosts;
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //get search term saved in shared prefs
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
        String search = prefs.getString(MainActivity.KEY_SEARCH,"android");

        rvPosts = findViewById(R.id.recyclerView);
        emptyView = findViewById(R.id.empty_view);

        // Initialize stackoverflow
        stackOFService = RetrofitInstance.getRetrofitInstance().create(GetStackOFDataService.class);
        stackOFService.getAnswers("DESC","stackoverflow", "creation", search).enqueue(new Callback<StackOFResult>() {
            @Override
            public void onResponse(Call<StackOFResult> call, Response<StackOFResult> response) {
                posts = response.body().getItems();

                if(posts.isEmpty()){
                    rvPosts.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                    return;
                }
                else {
                    rvPosts.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }

                for (Item i: posts) {
                    Log.i("stackoverflow", "Got post title: " + i.getTitle());

                }

                PostAdapter adapter= new PostAdapter(posts);

                // Attach the adapter to the recyclerview to populate items
                rvPosts.setAdapter(adapter);

                // Set layout manager to position the items
                rvPosts.setLayoutManager(new LinearLayoutManager(ResultsActivity.this));
            }

            @Override
            public void onFailure(Call<StackOFResult> call, Throwable t) {
                Log.i("stackoverflow", "Error: " + t.getMessage());
            }
        });

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