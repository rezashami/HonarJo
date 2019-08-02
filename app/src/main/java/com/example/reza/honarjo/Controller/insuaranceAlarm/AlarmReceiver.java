package com.example.reza.honarjo.Controller.insuaranceAlarm;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.R;
import com.example.reza.honarjo.View.insurance.InsuranceListActivity;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
    Context myContext;
    DBAlarm alarm;

    @Override
    public void onReceive(Context context, Intent intent) {
        String TAG = "AlarmReceiver";
        Log.e(TAG, "Alarm received");
        this.myContext = context;

        Bundle extras = intent.getExtras();
        Log.e(TAG, String.valueOf(extras != null));
        if (extras != null) {
            alarm = (DBAlarm) extras.getSerializable("Alarm");
        }

        Intent intent2 = new Intent(context, InsuranceListActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent2, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("هشدار اتمام بیمه")
                .setContentText("برای مشاهده ضربه بزنید")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000);
               // .setSound(Uri.parse("uri://sadfasdfasdf.mp3"));
        ;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(alarm.getId(), builder.build());
    }
}
