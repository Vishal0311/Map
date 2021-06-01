package com.vishal.newmap;

import android.content.BroadcastReceiver;

import android.content.Context;

import android.content.Intent;

import android.location.Location;

import com.google.android.gms.location.LocationResult;

import com.vishal.newmap.database.AppDatabase;

import com.vishal.newmap.database.LocationEntity;

public class LocationUpdateReceiver extends BroadcastReceiver {
    @Override

    public void onReceive(Context context, Intent intent) {

        LocationResult result = LocationResult.extractResult(intent);

        if (result != null && result.getLastLocation() != null) {

            Location currentLocation = result.getLastLocation();
         LocationEntity locationEntity = new LocationEntity();

            locationEntity.writeTs = System.currentTimeMillis() / 1000F;

            locationEntity.location = SimpleLocation.fromLocation(currentLocation);

            AppDatabase database = AppDatabase.getInstance(context);
           try {

                new Thread(() -> {

                    database.locationDao().insert(locationEntity);

                }) {{

                    start();

                }}.join();
         } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

}