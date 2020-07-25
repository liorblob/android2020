package com.example.stackoverflow.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.stackoverflow.database.DBHelper;
import com.example.stackoverflow.database.DataContract;
import com.example.stackoverflow.model.Search;

import java.util.ArrayList;
import java.util.List;

public class DBService {
    private DBHelper dbHelper;
    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;

    public DBService(Context context){
        dbHelper = new DBHelper(context);
        dbWrite = dbHelper.getWritableDatabase();
        dbRead = dbHelper.getReadableDatabase();
        Log.i("Database service", "Created database in " + context.getDatabasePath(DataContract.DB_NAME));
    }

    public void addSearch(String key, float locationX, float locationY) {
        ContentValues values = new ContentValues();
        values.put(DataContract.TableSearches.COLUMN_NAME_SEARCH_KEY, key);
        values.put(DataContract.TableSearches.COLUMN_NAME_SEARCH_LOCATION_X, locationX);
        values.put(DataContract.TableSearches.COLUMN_NAME_SEARCH_LOCATION_Y, locationY);

        long rowId = dbWrite.insert(DataContract.TableSearches.TABLE_NAME, null, values);
        Log.i("Database service", "Inserted row " + rowId + " to the database");
    }

    public List<Search> getSearches() {
        String[] projection = {DataContract.TableSearches.COLUMN_NAME_SEARCH_ID,
                DataContract.TableSearches.COLUMN_NAME_SEARCH_KEY,
                DataContract.TableSearches.COLUMN_NAME_SEARCH_LOCATION_X,
                DataContract.TableSearches.COLUMN_NAME_SEARCH_LOCATION_Y};

        List<Search> searches = new ArrayList<Search>();
        String sortOrder = DataContract.TableSearches.COLUMN_NAME_SEARCH_ID + " DESC";
        try (Cursor c = dbRead.query(
                DataContract.TableSearches.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder)) {

            if (c.moveToFirst()) {
                do {
                    Log.i("Database service", "Reading row " + c.getLong(0) + " from the database");
                    searches.add(new Search(
                            c.getLong(0),
                            c.getString(1),
                            c.getFloat(2),
                            c.getFloat(3)
                    ));
                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("Database service", "Error: " + e.getMessage());
        }

        return searches;
    }

    public void deleteSearch(long searchID) {
        String selection = DataContract.TableSearches.COLUMN_NAME_SEARCH_ID + " = ?";

        String[] selectionArgs = { String.valueOf(searchID) };

        dbRead.delete(DataContract.TableSearches.TABLE_NAME, selection, selectionArgs);
    }
}
