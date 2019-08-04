package com.example.reza.honarjo.Controller.alarmController;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String TAG = "AlarmServices ";
        Log.e(TAG,"Reboot is completed");
        Intent myIntent = new Intent(context, AlarmGroupBackgroundService.class);
        context.startService(myIntent);
    }
}
