package com.example.reza.honarjo.Controller.alarmSetter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.reza.honarjo.Controller.alarmController.AlarmReceiver;
import com.example.reza.honarjo.Model.alarm.DBAlarm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Intent.FILL_IN_DATA;

public class AlarmSetter {
    private final String TAG = "AlarmSetter";
    private final Context context;

    public AlarmSetter(Context context) {
        this.context = context;
    }

    public void setGroupAlarm(List<DBAlarm> input) {
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
            Intent intent = new Intent("MY.ACTION.ALARM");
            intent.setClass(context, AlarmReceiver.class);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out;
            try {
                out = new ObjectOutputStream(bos);
                out.writeObject(alarm);
                out.flush();
                byte[] data = bos.toByteArray();
                intent.putExtra("Alarm", data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            PendingIntent pendingIntent;
            pendingIntent = PendingIntent.getBroadcast(context, alarm.getId(), intent, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Log.e(TAG, "Set to: " + cal_alarm.getTime().toString());
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
    }

    public void setOneAlarm(DBAlarm alarm) {
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
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(alarm);
            out.flush();
            byte[] data = bos.toByteArray();
            intent.putExtra("Alarm", data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
//        Bundle b = new Bundle();
//        b.putSerializable("Alarm", alarm);
//        intent.putExtras(b);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(context, alarm.getId(), intent, FILL_IN_DATA);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Log.e(TAG, "Set to: " + cal_alarm.getTime().toString());
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

    public void cancelAlarm(DBAlarm alarm)
    {
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm.getId(), intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

}
