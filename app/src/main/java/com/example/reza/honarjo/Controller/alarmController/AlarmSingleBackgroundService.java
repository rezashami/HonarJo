package com.example.reza.honarjo.Controller.alarmController;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.reza.honarjo.Controller.DBInsurance.InsuranceRepository;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.Model.logger.DBLogger;
import com.example.reza.honarjo.R;
import com.example.reza.honarjo.View.insurance.InsuranceListActivity;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import saman.zamani.persiandate.PersianDate;

public class AlarmSingleBackgroundService extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
    DBAlarm alarm;

    public void setAlarm(DBAlarm _alarm) {
        class RUN implements Runnable{
            private final DBAlarm dbAlarm;
            private RUN(DBAlarm alarm)
            {
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

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // start showing notification
        Intent intent2 = new Intent(getApplicationContext(), InsuranceListActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("هشدار اتمام بیمه")
                .setContentText("برای مشاهده ضربه بزنید")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(_alarm.getId(), builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            alarm = (DBAlarm) extras.getSerializable("Alarm");
            setAlarm(alarm);
        }
        stopSelf();
        return START_NOT_STICKY;
    }
}
