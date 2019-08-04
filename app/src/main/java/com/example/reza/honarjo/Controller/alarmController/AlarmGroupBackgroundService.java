package com.example.reza.honarjo.Controller.alarmController;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.reza.honarjo.Controller.DBInsurance.InsuranceRepository;
import com.example.reza.honarjo.Controller.alarmSetter.AlarmSetter;
import com.example.reza.honarjo.Model.alarm.DBAlarm;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AlarmGroupBackgroundService extends Service {
    InsuranceRepository insuranceRepository;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        insuranceRepository = new InsuranceRepository(getApplication());
        try {
            List<DBAlarm> alarms = insuranceRepository.getDBAlarms();
            AlarmSetter alarmSetter = new AlarmSetter(getApplicationContext());
            alarmSetter.setGroupAlarm(alarms);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopSelf();
        return START_NOT_STICKY;
    }
}