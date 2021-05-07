package com.vishal.newmap;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TrackerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action = intent.getStringExtra("com.vishal.newmap.TRACKER_ACTIONS");
        if (action.equals(getString(R.string.tracker_action_start))) {
            handleStart(action);
        } else if (action.equals(getString(R.string.tracker_action_stop))) {
            handleStop(action);
        }

        return START_REDELIVER_INTENT;
    }

    private void handleStop(String action) {
        Log.i("TAG", "handleStop: "+action);
        new LocationUpdatesAction(getApplicationContext()).onStop().addOnSuccessListener(request -> {
            PreferenceStorage storage = new PreferenceStorage(this);
            String currentState = storage.getString(Constants.PREF_TRACKER_STATE, getString(R.string.tracker_state_ongoing));
            if(currentState.equals(getString(R.string.tracker_state_ongoing))){
                storage.putString(Constants.PREF_TRACKER_STATE, getString(R.string.tracker_state_stopped) );
            }
        }).addOnFailureListener(error->{
            Log.i("TrackerService", "Error while starting location update"+error);
        });
    }

    private void handleStart(String action) {
        Log.i("TAG", "handleStart: "+action);
        new LocationUpdatesAction(getApplicationContext()).onStart().addOnSuccessListener(request -> {
            PreferenceStorage storage = new PreferenceStorage(this);
            String currentState = storage.getString(Constants.PREF_TRACKER_STATE, getString(R.string.tracker_state_stopped));
            if(currentState.equals(getString(R.string.tracker_state_stopped))){
                storage.putString(Constants.PREF_TRACKER_STATE, getString(R.string.tracker_state_ongoing) );
            }
        }).addOnFailureListener(error->{
            Log.i("TrackerService", "Error while starting location update"+error);
        });
    }


}
