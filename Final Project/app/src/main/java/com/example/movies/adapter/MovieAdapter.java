package com.example.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movies.R;
import com.example.movies.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    // - 2. add DATA member var
    // -initialize the var via constructor
    // - implement getItemCount()
    private List<Movie> movieResults;

    // 2.1initialize the var via constructor
    public MovieAdapter(List<Movie> movieResults) {
        this.movieResults = movieResults;
    }

    // 3. implement onCreateViewHolder

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
       // implement onCreateViewHolder
        // inflating a layout from XML and returning the holder

        //get inflater
        //the parent is actually the RecyclerView

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //by using inflater.inflate() you create your View from your XML file.
        View movieView = inflater.inflate(R.layout.movie_card_view, parent, false);

        // Return a new holder instance
        MovieViewHolder viewHolder = new MovieViewHolder(movieView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        //implement onBindViewHolder()
        // set data into the item through holder

        // Get the data model based on position
        Movie movie = movieResults.get(position);

        // Set item views based on your views and data model
        holder.mMovieTitle.setText(movie.getTitle());
        holder.mMovieDesc.setText(movie.getOverview());

        /**
         * Using Glide to handle image loading.
         * Learn more about Glide here:
         * <a href="http://blog.grafixartist.com/image-gallery-app-android-studio-1-4-glide/" />
         */
        Glide
                .with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                .centerCrop()
                .into(holder.mPosterImg);


    }


    // -2.2. implement getItemCount()
    @Override
    public int getItemCount() {
        return this.movieResults.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        // 1. member variables
        // and initialize in constructor
        private TextView mMovieTitle;
        private TextView  mMovieDesc;
        private ImageView mPosterImg;



        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
          //1.1  initialize member variables in constructor
            mMovieTitle = itemView.findViewById(R.id.movie_title);
            mMovieDesc =  itemView.findViewById(R.id.movie_desc);
            mPosterImg =  itemView.findViewById(R.id.movie_poster);

        }


    }
}
