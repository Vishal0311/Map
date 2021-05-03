package com.vishal.newmap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.google.android.gms.location.LocationResult;

public class LocationUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LocationResult result =  LocationResult.extractResult(intent);
        if(result != null){
            Location currentLocation = result.getLastLocation();
        }
    }
}
