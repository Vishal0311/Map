package com.vishal.newmap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TrackerActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TrackerActionReceiver", "onReceive(" +context + "," + intent + ")");
        Intent serviceIntent = new Intent(context, TrackerService.class);
        serviceIntent.putExtra("com.vishal.newmap.TRACKER_ACTIONS", intent.getAction());
        context.startService(serviceIntent);
    }
}
