package com.example.stackoverflow;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stackoverflow.model.Search;
import com.example.stackoverflow.services.DBService;
import com.example.stackoverflow.services.LocationService;

import java.util.List;


public class MainActivity extends AppCompatActivity implements LocationListener {
    private DBService dbService;
    private LocationService locationService;
    public static final String EXTRA_SEARCH = "com.example.stackoverflow.SEARCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbService = new DBService(this);
        locationService = new LocationService(this, this);

    }

    public void search(View v){
        EditText editText = findViewById(R.id.search_text);
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

        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra(EXTRA_SEARCH, searchText);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {
        locationService.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
