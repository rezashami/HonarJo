package com.example.reza.honarjo.Controller.insuaranceAlarm;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.reza.honarjo.Controller.DBInsurance.InsuranceRepository;
import com.example.reza.honarjo.Model.alarm.DBAlarm;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AlarmService extends BroadcastReceiver {
    InsuranceRepository insuranceRepository;
    private String TAG = "AlarmServices ";
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG,"Reboot is completed");
        insuranceRepository = new InsuranceRepository((Application) context.getApplicationContext());
        try {
            List <DBAlarm> alarms = insuranceRepository.getDBAlarms();
            setAlarm(alarms,context);
        } catch (ExecutionException e) {
            Log.e(TAG,e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            Log.e(TAG,e.getMessage());
            e.printStackTrace();
        }
    }
    private void setAlarm(List<DBAlarm> input, Context context) {
        for (int i = 0; i < input.size(); i++) {
            DBAlarm alarm = input.get(i);
            Calendar cal_alarm = Calendar.getInstance();
            if (alarm.getMyDate() == null) {
                cal_alarm.setTime(new Date());
                cal_alarm.add(Calendar.DATE, -1);
                cal_alarm.set(Calendar.HOUR, 17);
                cal_alarm.set(Calendar.MINUTE, 0);
                cal_alarm.set(Calendar.SECOND, 0);
            } else {
                cal_alarm.setTime(alarm.getMyDate());
                cal_alarm.set(Calendar.HOUR, 17);
                cal_alarm.set(Calendar.MINUTE, 0);
                cal_alarm.set(Calendar.SECOND, 0);
            }
            Intent intent = new Intent(context, AlarmReceiver.class);
            Bundle b = new Bundle();
            b.putSerializable("Alarm", alarm);
            intent.putExtras(b);
            PendingIntent pendingIntent;
            pendingIntent = PendingIntent.getBroadcast(context, alarm.getId(), intent, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Log.e(TAG,"Set to: " + cal_alarm.getTime().toString() + " and interval is: 7");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (alarmManager != null) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
                }
            } else {
                if (alarmManager != null) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
                }
            }
        }

        //7 * 24 * 60 * 60 * 1000
    }

}
