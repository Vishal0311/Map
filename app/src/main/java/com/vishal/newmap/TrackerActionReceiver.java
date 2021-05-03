package com.vishal.newmap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TrackerActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TrackerActionReceiver", "onReceive(" +context + "," + intent + ")");
    }
}
