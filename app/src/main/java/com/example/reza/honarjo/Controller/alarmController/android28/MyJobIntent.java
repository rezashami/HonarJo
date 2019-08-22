package com.example.reza.honarjo.Controller.alarmController.android28;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import com.example.reza.honarjo.Controller.DBInsurance.InsuranceRepository;
import com.example.reza.honarjo.Controller.alarmSetter.AlarmSetter;
import com.example.reza.honarjo.Controller.parcelableUtil.ParcelableUtil;
import com.example.reza.honarjo.Model.alarm.DBAlarm;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyJobIntent extends JobIntentService {
    static final int ALARM_SET_JOB_ID = 100;
    private static final String TAG = MyJobIntent.class.getSimpleName();
    private static final String ACTION_LOAD = "action.LOAD_DATA";
    private static final String ACTION_SINGLE = "action.SINGLE";

    public static void enqueueLoadWork(Context context) {
        Intent intent = new Intent(context, MyJobIntent.class);
        intent.setAction(ACTION_LOAD);
        enqueueWork(context, MyJobIntent.class, ALARM_SET_JOB_ID, intent);
    }

    public static void enqueueSingleWork(Context context, Bundle bundle) {
        Intent intent = new Intent(ACTION_SINGLE);
        intent.setClass(context, MyJobIntent.class);
        intent.putExtras(bundle);
        enqueueWork(context, MyJobIntent.class, ALARM_SET_JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_LOAD: {
                    InsuranceRepository insuranceRepository = new InsuranceRepository(getApplication());
                    List<DBAlarm> alarms = null;
                    try {
                        alarms = insuranceRepository.getDBAlarms();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (alarms == null)
                        break;
                    AlarmSetter alarmSetter = new AlarmSetter(getApplicationContext());
                    alarmSetter.setGroupAlarm(alarms);
                    break;
                }

                case ACTION_SINGLE: {
                    DBAlarm dbAlarm = ParcelableUtil.getFromByteArray(intent.getByteArrayExtra("Alarm"));
                    if (dbAlarm != null) {
                        AlarmSetter alarmSetter = new AlarmSetter(getApplicationContext());
                        alarmSetter.showNotification(dbAlarm, getApplication());
                        Log.e(TAG, "set the alarm ");
                    } else {
                        Log.e(TAG, "DbAlarm is null");
                        Log.e(TAG, "Is extra is null " + (intent.getExtras() == null));
//                        for (String key : intent.getExtras().keySet()) {
//                            Log.d(TAG, key + " is a key in the bundle");
//                        }
                    }

                    break;
                }
            }
        }
    }
}
