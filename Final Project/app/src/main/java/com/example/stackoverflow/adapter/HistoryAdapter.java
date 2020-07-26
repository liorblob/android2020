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
import com.example.stackoverflow.R;
import com.example.stackoverflow.ResultsActivity;
import com.example.stackoverflow.model.Search;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<Search> searches;

    public HistoryAdapter(List<Search> searches) {
        this.searches = searches;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View searchView = inflater.inflate(R.layout.history_card_view,parent,false);
        return new HistoryViewHolder(searchView);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Search search = searches.get(position);
        holder.searchID.setText(""+search.getSearchID());
        holder.searchItem.setText(search.getSearchKey());
        String apiKey = "An05BFKUEHccF9x7EZqu1fVA8gpFOc-YMdaN-fsy9ZzFBmvlTIWe2iAowhYm6SPQ";

        String map = "https://dev.virtualearth.net/REST/V1/Imagery/Map/Road/"+
                search.getLocationY() + "%2C" + search.getLocationX() +
                "/15?mapSize=400,300&format=png&pushpin=" +
                search.getLocationY() +"," +search.getLocationX() + ";21;" + search.getSearchKey() +
                "&key="+ apiKey;


        Glide.with(holder.itemView.getContext())
                    .load(map)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                    .centerCrop()
                    .into(holder.mapView);



    }

    @Override
    public int getItemCount() {
        return this.searches.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView searchID;
        private TextView searchItem;
        private ImageView mapView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            searchID = itemView.findViewById(R.id.searchID);
            searchItem = itemView.findViewById(R.id.searchItem);
            mapView = itemView.findViewById(R.id.mapImage);

            searchItem.setOnClickListener(this);
        }

        private void saveSharedPrefs(String searchText){

        }
        @Override
        public void onClick(View v) {
            SharedPreferences pref =v.getContext().getSharedPreferences(MainActivity.PREFS,MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(MainActivity.KEY_SEARCH,this.searchItem.getText().toString());
            editor.commit();
            v.getContext().startActivity(new Intent(v.getContext(), ResultsActivity.class));
        }
    }
}
