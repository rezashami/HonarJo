package com.example.reza.honarjo.Controller.alarmController;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    Context myContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.myContext = context;
        Bundle extras = intent.getExtras();
        Log.e("Recived", String.valueOf(extras != null));

        Intent myIntent = new Intent(context, AlarmSingleBackgroundService.class);
        if (extras != null) {
            Log.e("Extra in Receiver", extras.toString());
            myIntent.putExtras(extras);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e("Start", "startForegroundService");
            context.startForegroundService(myIntent);
        } else {
            Log.e("Start", "startService");
            context.startService(myIntent);
        }
    }
}
