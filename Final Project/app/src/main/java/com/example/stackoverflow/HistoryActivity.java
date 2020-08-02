package com.example.stackoverflow;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stackoverflow.adapter.HistoryAdapter;
import com.example.stackoverflow.handlers.SwipeToDeleteCallback;
import com.example.stackoverflow.model.Search;
import com.example.stackoverflow.services.DBService;
import com.example.stackoverflow.services.DispatcherService;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class HistoryActivity extends BaseActivity {

    private DBService dbService;
    private List<Search> searches;
    private RecyclerView historyRecycler;
    private HistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbService = new DBService(this);
        searches = dbService.getSearches();
        historyRecycler = findViewById(R.id.historyRecyclerView);

        mAdapter = new HistoryAdapter(searches);
        historyRecycler.setAdapter(mAdapter);
        historyRecycler.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
        enableSwipeToDeleteAndUndo();
    }


    @Override
    protected void onPause() {
        super.onPause();

        //Save As last Activity
        DispatcherService.saveActivity(this);
    }


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Search item = mAdapter.getSearches().get(position);

                mAdapter.removeItem(position);

                LinearLayout linearLayout = findViewById(R.id.historyLinear);
                Snackbar snackbar = Snackbar
                        .make(linearLayout, getString(R.string.delete_notification), Snackbar.LENGTH_LONG)
                        .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                if (event != DISMISS_EVENT_ACTION) {
                                    // Dismiss wasn't because of tapping "UNDO"
                                    dbService.deleteSearch(item.getSearchID());
                                }
                            }
                        });
                snackbar.setAction(getString(R.string.undo_btn), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        historyRecycler.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();


            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(historyRecycler);
    }
}