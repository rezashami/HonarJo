package com.example.reza.honarjo.Controller.alarmController;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.reza.honarjo.Controller.alarmSetter.AlarmSetter;
import com.example.reza.honarjo.Controller.parcelableUtil.ParcelableUtil;
import com.example.reza.honarjo.Model.alarm.DBAlarm;

public class AlarmSingleBackgroundService extends Service {
    DBAlarm alarm;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            alarm = ParcelableUtil.getFromByteArray(intent.getByteArrayExtra("Alarm"));
            AlarmSetter alarmSetter = new AlarmSetter(getApplicationContext());
            alarmSetter.showNotification(alarm, getApplication());

        }
        stopSelf();
        return START_NOT_STICKY;
    }
}
