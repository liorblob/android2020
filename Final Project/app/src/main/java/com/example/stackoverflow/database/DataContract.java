package com.example.stackoverflow.database;

import android.provider.BaseColumns;

public final class DataContract {

    public DataContract() {}
    public static final String DB_NAME = "StackOFDatabase.db";

    public static abstract class TableSearches implements BaseColumns {
        public static final String TABLE_NAME = "searches";
        public static final String COLUMN_NAME_SEARCH_ID = "search_id";
        public static final String COLUMN_NAME_SEARCH_KEY = "search_key";
        public static final String COLUMN_NAME_SEARCH_LOCATION_X = "search_location_x";
        public static final String COLUMN_NAME_SEARCH_LOCATION_Y = "search_location_y";
    }
}
