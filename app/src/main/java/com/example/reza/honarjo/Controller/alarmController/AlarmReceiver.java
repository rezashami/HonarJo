package com.example.reza.honarjo.Controller.alarmController;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmReceiver extends BroadcastReceiver {
    Context myContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.myContext = context;
        Bundle extras = intent.getExtras();
        Intent myIntent = new Intent(context, AlarmSingleBackgroundService.class);
        if (extras != null) {
            myIntent.putExtras(extras);
        }
        context.startService(myIntent);
    }
}
