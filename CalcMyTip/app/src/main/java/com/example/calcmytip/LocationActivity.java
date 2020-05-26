package com.example.calcmytip;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LocationActivity extends BaseActivity implements LocationListener {

    LocationManager lm;
    private final int REQUEST_PERMISSION_LOCATION_STATE = 1;
    boolean locationGranted = false;
    boolean firstTime = true;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("LocationListener:", "Starting Location activity");

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        setContentView(R.layout.activity_location);
        web = findViewById(R.id.webView);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("LocationListener:", "Requesting permissions");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION_STATE);

        } else {
            Toast.makeText(LocationActivity.this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
            showMap();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Save As last Activity
        Dispatcher.saveActivity(this);

    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public void onLocationChanged(Location location) {
        showMap();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_LOCATION_STATE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationGranted = true;
                Toast.makeText(LocationActivity.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
            } else {
                locationGranted = false;
                Toast.makeText(LocationActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }

        showMap();
    }

    private void showMap(){
        Log.i("LocationListener:", "Launching map");
        Location location;

        if(locationGranted){
            try {
                if (firstTime) {
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5 * 1000, 5, this);
                    firstTime = false;
                }
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            catch (SecurityException e){
                Toast.makeText(LocationActivity.this, "No permission.", Toast.LENGTH_SHORT).show();
                return;
            }

            web.setWebViewClient(new WebViewClient());
            WebSettings settings=web.getSettings();
            settings.setJavaScriptEnabled(true);

            searchRestaurants(location);
        }
    }

    private void searchRestaurants(Location location) {
        double longitude =location.getLongitude();
        double latitude=location.getLatitude();

        Log.i("LocationListener:", "Searching restaurants in location: " + latitude + ',' + longitude);
        // display restaurants near the location
        String strUrl="https://www.google.com/maps/search/restaurants/@"+latitude+","+longitude+",16z";

        web.loadUrl(strUrl);
    }
}

