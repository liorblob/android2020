package com.example.stackoverflow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stackoverflow.services.DBService;
import com.example.stackoverflow.services.DispatcherService;
import com.example.stackoverflow.services.LocationService;


public class MainActivity extends AppCompatActivity implements LocationListener {
    private DBService dbService;
    private LocationService locationService;
    EditText editText;

    public static final String PREFS = "prefsKey";
    public static final String KEY_SEARCH = "searchKey";
    public static final String KEY_POST_URL = "postURLKey";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbService = new DBService(this);
        locationService = new LocationService(this, this);
        editText = findViewById(R.id.search_text);

    }

    private void saveSharedPrefs(String searchText){
        SharedPreferences pref = getSharedPreferences(PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_SEARCH,searchText);
        editor.commit();
    }

    public void search(View v){

        String searchText = editText.getText().toString();
        float locationX = 0;
        float locationY = 0;

        Location currentLocation = locationService.getLocation(this);
        if(currentLocation != null) {
            locationX = (float) currentLocation.getLongitude();
            locationY = (float) currentLocation.getLatitude();
        } else {
            Log.w("Main activity", "Failed getting location.");
        }

        dbService.addSearch(searchText, locationX, locationY);

        //Save search term in shared prefs for next activity
        saveSharedPrefs(searchText);
        startActivity( new Intent(this, ResultsActivity.class));
    }
    // Temporary - will be moved to actionbar
    public void goHistory(View view) {
        startActivity(new Intent(this,HistoryActivity.class));
    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {
        locationService.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSharedPrefs(editText.getText().toString());
        DispatcherService.resetActivity(this);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}
