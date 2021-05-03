package com.vishal.newmap;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

public class LocationUpdatesAction {

    private  Context mContext;

    public LocationUpdatesAction(Context context) {

        this.mContext = context;
    }


    Intent trackerIntent = new Intent(mContext, TrackerService.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(
            mContext, 0, trackerIntent,  PendingIntent.FLAG_UPDATE_CURRENT);
    LocationRequest locationRequest = LocationRequest.create()
            .setInterval(5000)
            .setSmallestDisplacement(40F) // In meters
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


    public  Task<Void> onStart(){
        try {
            return LocationServices.getFusedLocationProviderClient(mContext).requestLocationUpdates(locationRequest,pendingIntent);
        }catch (SecurityException e){
            Toast.makeText(mContext, "Task<Void>", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    public  Task<Void> onStop(){
        return null;
    }


}