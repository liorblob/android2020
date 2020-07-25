package com.example.stackoverflow.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = DataContract.DB_NAME;
    private static final String TEXT_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + DataContract .TableSearches.TABLE_NAME  + " (" +
                    DataContract.TableSearches.COLUMN_NAME_SEARCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    DataContract.TableSearches.COLUMN_NAME_SEARCH_KEY + TEXT_TYPE + COMMA_SEP +
                    DataContract.TableSearches.COLUMN_NAME_SEARCH_LOCATION_X + FLOAT_TYPE + COMMA_SEP +
                    DataContract.TableSearches.COLUMN_NAME_SEARCH_LOCATION_Y + FLOAT_TYPE + " );";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + DataContract .TableSearches.TABLE_NAME;
    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);        }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);        }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);        }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}


