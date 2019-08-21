package com.example.reza.honarjo.Controller.alarmController.android28;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.reza.honarjo.Controller.DBInsurance.InsuranceRepository;
import com.example.reza.honarjo.Controller.alarmSetter.AlarmSetter;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.Model.logger.DBLogger;
import com.example.reza.honarjo.R;
import com.example.reza.honarjo.View.insurance.InsuranceListActivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import saman.zamani.persiandate.PersianDate;

public class MyJobIntent extends JobIntentService {
    public static final String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
    static final int DOWNLOAD_JOB_ID = 100;
    private static final String TAG = MyJobIntent.class.getSimpleName();
    private static final String ACTION_LOAD = "action.LOAD_DATA";
    private static final String ACTION_SINGLE = "action.SINGLE";

    public static void enqueueLoadWork(Context context) {
        Intent intent = new Intent(context, MyJobIntent.class);
        intent.setAction(ACTION_LOAD);
        enqueueWork(context, MyJobIntent.class, DOWNLOAD_JOB_ID, intent);
    }

    public static void enqueueSingleWork(Context context, Bundle bundle) {
        Intent intent = new Intent(ACTION_SINGLE);
        intent.setClass(context, MyJobIntent.class);
        intent.putExtras(bundle);
        enqueueWork(context, MyJobIntent.class, DOWNLOAD_JOB_ID, intent);
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
                    ByteArrayInputStream bis = new ByteArrayInputStream(intent.getByteArrayExtra("Alarm"));
                    ObjectInput in = null;
                    DBAlarm dbAlarm = null;
                    try {
                        in = new ObjectInputStream(bis);
                        dbAlarm = (DBAlarm) in.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (in != null) {
                                in.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (dbAlarm != null)
                    {
                        setAlarm(dbAlarm);
                        Log.e(TAG,"set the alarm ");
                    }
                    else
                    {
                        Log.e(TAG,"DbAlarm is null");
                        Log.e(TAG, "Is extra is null "+ (intent.getExtras() == null));
                        for (String key: intent.getExtras().keySet())
                        {
                            Log.d (TAG, key + " is a key in the bundle");
                        }
                    }

                    break;
                }
            }
        }
    }


    private void setAlarm(DBAlarm _alarm) {
        class RUN implements Runnable {
            private final DBAlarm dbAlarm;

            private RUN(DBAlarm alarm) {
                this.dbAlarm = alarm;
            }

            @Override
            public void run() {
                DBLogger report = new DBLogger();
                PersianDate persianDate = new PersianDate(new Date().getTime());
                String header = "نمایش هشدار، در تاریخ: " + persianDate.toString();
                report.setHeader(header);
                report.setBody(dbAlarm.toString());
                report.setDate(new Date());
                DatabaseManager databaseHelper = DatabaseManager.getDatabase(getApplicationContext());
                databaseHelper.daoAccess().insertLog(report);
            }
        }
        new Thread(new RUN(_alarm)).start();
        InsuranceRepository insuranceRepository = new InsuranceRepository(getApplication());
        try {
            DBAlarm dbAlarm = insuranceRepository.getOneDBAlarmByID(1);
            if (dbAlarm.getId() != _alarm.getId()) {
                for (int i = 0; i < _alarm.getUsers().size(); i++)
                    dbAlarm.addUser(_alarm.getUsers().get(i));
                insuranceRepository.removeDBAlarm(_alarm);
            }
            insuranceRepository.update(dbAlarm);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // start showing notification
        Intent intent2 = new Intent(getApplicationContext(), InsuranceListActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 0);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("هشدار اتمام بیمه")
                .setContentText("برای مشاهده ضربه بزنید")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000).setSound(alarmSound).setOnlyAlertOnce(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(_alarm.getId(), builder.build());
    }
}
