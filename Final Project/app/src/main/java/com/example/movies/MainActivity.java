package com.example.movies;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.adapter.MovieAdapter;
import com.example.movies.model.Movie;
import com.example.movies.model.MoviePageResult;
import com.example.movies.network.GetMovieDataService;
import com.example.movies.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GetMovieDataService movieService;
    List<Movie> movies;
    RecyclerView rvMovies;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies=findViewById(R.id.recyclerView);


        // Initialize movies
        movieService = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
        movieService.getTopRatedMovies(1,"e593d7d46d234eab7cf8a135ea8c8e2d").enqueue(new Callback<MoviePageResult>() {
            @Override
            public void onResponse(Call<MoviePageResult> call, Response<MoviePageResult> response) {
                movies=response.body().getMovies();

                for (Movie m:movies) {
                    Log.i("movies", m.getTitle());

                }

                MovieAdapter adapter= new MovieAdapter(movies);

                // Attach the adapter to the recyclerview to populate items
                rvMovies.setAdapter(adapter);

                // Set layout manager to position the items
                rvMovies.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            }

            @Override
            public void onFailure(Call<MoviePageResult> call, Throwable t) {

            }
        });

    }
}
