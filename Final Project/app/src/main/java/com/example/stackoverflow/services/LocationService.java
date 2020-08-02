package com.example.stackoverflow.services;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import static android.content.Context.LOCATION_SERVICE;

public class LocationService {
    LocationManager lm;
    private final int REQUEST_PERMISSION_LOCATION_STATE = 1;
    private boolean locationGranted = false;
    private boolean firstTime = true;
    private Context context;


    public LocationService(Context context, Activity activity){
        this.context = context;
        Log.i("Location Service", "Starting Location service");

        lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("Location Service", "Requesting permissions");
            locationGranted = false;
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION_STATE);

        } else {
            locationGranted = true;

        }
    }

    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_LOCATION_STATE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationGranted = true;
                Log.i("Location Service", "Permission granted.");
                Toast.makeText(context, "Permission Granted!", Toast.LENGTH_SHORT).show();
            } else {
                locationGranted = false;
                Log.i("Location Service", "Permission denied.");
                Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Location getLocation(LocationListener listener){
        Log.i("Location Service", "Getting location");
        Location location;

        if(locationGranted){
            try {
                if (firstTime) {
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5 * 1000, 5, listener);
                    firstTime = false;
                }
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            catch (SecurityException e){
                Log.e("Location Service", "No permissions.");
                Toast.makeText(context, "No permission.", Toast.LENGTH_SHORT).show();
                return null;
            }

            return location;
        }
        Log.e("Location Service", "Location was not granted.");
        Toast.makeText(context, "Location was not granted.", Toast.LENGTH_SHORT).show();
        return null;
    }
}
