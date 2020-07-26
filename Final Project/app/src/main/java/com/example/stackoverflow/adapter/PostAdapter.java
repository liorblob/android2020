package com.example.stackoverflow.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.stackoverflow.MainActivity;
import com.example.stackoverflow.PostViewActivity;
import com.example.stackoverflow.R;
import com.example.stackoverflow.model.Item;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Item> posts;

    public PostAdapter(List<Item> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.post_card_view, parent, false);

        return new PostViewHolder(postView);
    }


    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Item answer = posts.get(position);

        holder.postTitle.setText(answer.getTitle());
        holder.postLink.setText(answer.getLink());

        Glide.with(holder.itemView.getContext())
                .load(answer.getOwner().getProfileImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                .centerCrop()
                .into(holder.postUserImage);


    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView postTitle;
        private TextView postLink;
        private ImageView postUserImage;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postTitle = itemView.findViewById(R.id.post_title);
            postLink =  itemView.findViewById(R.id.post_link);
            postUserImage =  itemView.findViewById(R.id.user_image);

            postLink.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            SharedPreferences pref =v.getContext().getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(MainActivity.KEY_POST_URL,this.postLink.getText().toString());
            editor.commit();
            v.getContext().startActivity(new Intent(v.getContext(), PostViewActivity.class));
        }
    }
}
