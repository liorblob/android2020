package com.example.calcmytip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class Dispatcher extends Activity {

    public static final String DISPATCH = "dispatch";
    public static final String LAST_ACTIVITY = "lastActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class<?> activityClass;

        try {
            SharedPreferences prefs = getSharedPreferences(DISPATCH, MODE_PRIVATE);
            activityClass = Class.forName(prefs.getString(LAST_ACTIVITY, MainActivity.class.getName()));
        } catch(ClassNotFoundException ex) {
            activityClass = MainActivity.class;
        }
        //Log
        Log.i("Shared Preferences:", "in dispatcher");
        Log.i("Shared Preferences:", "going to activity: "+activityClass.getSimpleName());

        startActivity(new Intent(this, activityClass));
    }

    public static void saveActivity(Context activity){
        SharedPreferences prefs = activity.getSharedPreferences(DISPATCH, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LAST_ACTIVITY,activity.getClass().getName());
        editor.commit();
        Log.i("Shared Preferences:", "saving activity: "+activity.getClass().getSimpleName());
    }
}
