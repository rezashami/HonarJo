package com.example.reza.honarjo.Controller.alarmController;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.reza.honarjo.Controller.alarmController.android28.MyJobIntent;

public class AlarmReceiver extends BroadcastReceiver {
    Context myContext;
    private static final String TAG = AlarmReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        this.myContext = context;
        String action = intent.getAction();
        if (action != null && action.equals("MY.ACTION.ALARM")) {
            Bundle extras = intent.getExtras();
            Log.e(TAG, String.valueOf(extras != null));
            Intent myIntent = new Intent(context, AlarmSingleBackgroundService.class);
            if (extras != null) {
                Log.e(TAG,extras.keySet().size()+" The size of extra keys");
                for (String key: extras.keySet())
                {
                    Log.d (TAG, key + " is a key in the bundle");
                }
                myIntent.putExtras(extras);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.e(TAG, "Enqueue to jobIntentService");
                MyJobIntent.enqueueSingleWork(context, extras);
            } else {
                Log.e(TAG, "startService");
                context.startService(myIntent);
            }
        }
    }
}
