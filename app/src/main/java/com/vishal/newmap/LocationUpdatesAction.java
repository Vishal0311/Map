package com.vishal.newmap;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

public class LocationUpdatesAction {

    private  Context mContext;

    public LocationUpdatesAction(Context context) {

        this.mContext = context;
    }



    LocationRequest locationRequest = LocationRequest.create()
            .setInterval(5000)
            .setSmallestDisplacement(40F) // In meters
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


    public  Task<Void> onStart(){
        try {
            return LocationServices.getFusedLocationProviderClient(mContext).requestLocationUpdates(locationRequest,getPendingIntent());
        }catch (SecurityException e){
            Log.i("LocationUpdatesAction", "Error while starting location update"+e);
        }
        return null;
    }

    public  Task<Void> onStop(){
        return LocationServices.getFusedLocationProviderClient(mContext).removeLocationUpdates(
                getPendingIntent());

    }
    private PendingIntent getPendingIntent() {
        Intent trackerIntent = new Intent(mContext, LocationUpdateReceiver.class);
        return PendingIntent.getBroadcast(
                mContext, 0, trackerIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
