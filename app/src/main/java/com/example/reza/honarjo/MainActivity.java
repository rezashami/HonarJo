package com.example.reza.honarjo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.reza.honarjo.Controller.DBInsurance.InsuranceRepository;
import com.example.reza.honarjo.Controller.DBUser.UserRepository;
import com.example.reza.honarjo.Controller.alarmSetter.AlarmSetter;
import com.example.reza.honarjo.Controller.prefrence.PreferenceManager;
import com.example.reza.honarjo.Controller.timeConverter.TimeConverter;
import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.Model.queryResults.ExpireNameFamilyID;
import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.Model.users.ShowingUser;
import com.example.reza.honarjo.View.history.HistoryActivity;
import com.example.reza.honarjo.View.insurance.InsuranceListActivity;
import com.example.reza.honarjo.View.setting.SettingActivity;
import com.example.reza.honarjo.View.user.UserListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
    public static MainActivity mainActivity;
    List<DBUSer> users;
    PreferenceManager preferenceManager;
    InsuranceRepository insuranceRepository;
    UserRepository userRepository;
    List<DBAlarm> alarms;
    private String TAG = "MainActivity";
    private AlarmSetter alarmSetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        mainActivity = this;
        alarmSetter = new AlarmSetter(getApplicationContext());
        findViewById(R.id.main_content_users).setOnClickListener(v -> {
            Intent myIntent = new Intent(getApplicationContext(), UserListActivity.class);
            startActivity(myIntent);
        });
        findViewById(R.id.main_content_insurances).setOnClickListener(v -> {
            Intent myIntent = new Intent(getApplicationContext(), InsuranceListActivity.class);
            startActivity(myIntent);
        });
        findViewById(R.id.main_content_history).setOnClickListener(v -> {
            Intent myIntent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(myIntent);
        });
        findViewById(R.id.main_content_setting).setOnClickListener(v -> {
            Intent myIntent = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(myIntent);
        });
        preferenceManager = new PreferenceManager(getApplicationContext());
        if (preferenceManager.inc() == -1) {
            preferenceManager.setInc(2);
        }
        insuranceRepository = new InsuranceRepository(getApplication());
        userRepository = new UserRepository(getApplication());
        if (!preferenceManager.FirstLaunch()) {
            users = loadFromFile();
            Log.e(TAG, "Insert Finished");
            preferenceManager.setFirstTimeLaunch(true);
            userRepository.insertMany(users);
            alarms = fetchAlarmModel();
            insuranceRepository.insertMany(alarms);
        }
        findViewById(R.id.progressBar).setVisibility(View.GONE);
        findViewById(R.id.content_main).setVisibility(View.VISIBLE);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

//    private void setAlarm(List<DBAlarm> input) {
//        for (int i = 0; i < input.size(); i++) {
//            DBAlarm alarm = input.get(i);
//            Calendar cal_alarm = Calendar.getInstance();
//            if (alarm.getMyDate() == null) {
//                cal_alarm.setTime(new Date());
//                cal_alarm.add(Calendar.DATE, -1);
//                cal_alarm.set(Calendar.HOUR, 17);
//                cal_alarm.set(Calendar.MINUTE, 0);
//                cal_alarm.set(Calendar.SECOND, 0);
//            } else {
//                cal_alarm.setTime(alarm.getMyDate());
//                cal_alarm.set(Calendar.HOUR, 17);
//                cal_alarm.set(Calendar.MINUTE, 0);
//                cal_alarm.set(Calendar.SECOND, 0);
//            }
//            Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
//            Bundle b = new Bundle();
//            b.putSerializable("Alarm", alarm);
//            intent.putExtras(b);
//            PendingIntent pendingIntent;
//            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), alarm.getId(), intent, 0);
//            AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//            Log.e(TAG, "Set to: " + cal_alarm.getTime().toString() + " and interval is: 7");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                if (alarmManager != null) {
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
//                }
//            } else {
//                if (alarmManager != null) {
//                    alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
//                }
//            }
//        }
//
//        //7 * 24 * 60 * 60 * 1000
//    }
//
//    void tempAlarm() {
//
//        Calendar cal_alarm = Calendar.getInstance();
//        cal_alarm.set(cal_alarm.get(Calendar.YEAR), cal_alarm.get(Calendar.MONTH), cal_alarm.get(Calendar.DAY_OF_MONTH), cal_alarm.get(Calendar.HOUR_OF_DAY), (cal_alarm.get(Calendar.MINUTE) + 1) % 60, cal_alarm.get(Calendar.SECOND));
//
//
//        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
//        Bundle b = new Bundle();
//        b.putSerializable("Alarm", "Alarm");
//        intent.putExtras(b);
//        PendingIntent pendingIntent;
//        int _id = preferenceManager.inc();
//        Log.e(TAG, String.valueOf(_id));
//        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), _id, intent, 0);
//        _id++;
//        preferenceManager.setInc(_id);
//        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        Log.e(TAG, "Set to: " + cal_alarm.getTime().toString() + " and interval is: 7");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (alarmManager != null) {
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
//            }
//        } else {
//            if (alarmManager != null) {
//                alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
//            }
//        }
//    }

    String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getApplicationContext().getAssets().open("users.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    Date getDate(JSONObject obj) {
        int year = 0;
        int month = 0;
        int day = 0;
        try {
            year = obj.getInt("year");
            month = obj.getInt("month");
            day = obj.getInt("day");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return TimeConverter.getGEOTime(year, month, day);
    }

    List<DBUSer> loadFromFile() {
        List<DBUSer> result = new ArrayList<>();
        try {
            JSONArray total = new JSONArray(loadJSONFromAsset());
            Log.e(TAG, String.valueOf(total.length()));
            for (int i = 0; i < total.length(); i++) {
                JSONObject obj = total.getJSONObject(i);
                String name = obj.getString("name");
                String family = obj.getString("family");
                String mobileNumber = obj.getString("phoneNumber");
                Date registerDay = !obj.isNull("registerDay") ? getDate(obj.getJSONObject("registerDay")) : null;
                Date expireDay = !obj.isNull("expireDay") ? getDate(obj.getJSONObject("expireDay")) : null;
                Date yellowDay = !obj.isNull("yellowDay") ? getDate(obj.getJSONObject("yellowDay")) : null;
                Date orangeDay = !obj.isNull("orangeDay") ? getDate(obj.getJSONObject("orangeDay")) : null;
                Date greenDay = !obj.isNull("greenDay") ? getDate(obj.getJSONObject("greenDay")) : null;
                Date blueDay = !obj.isNull("blueDay") ? getDate(obj.getJSONObject("blueDay")) : null;
                Date brownDay = !obj.isNull("brownDay") ? getDate(obj.getJSONObject("brownDay")) : null;
                Date blackDay = !obj.isNull("blackDay") ? getDate(obj.getJSONObject("blackDay")) : null;
                boolean _private = obj.getBoolean("private");
                int code = obj.getInt("code");
                preferenceManager.setUserCode(code);
                result.add(new DBUSer(name, family, mobileNumber, registerDay, expireDay, yellowDay, orangeDay, greenDay, blueDay, brownDay, blackDay, _private, code));
                Log.e(TAG, "Add to db" + i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    List<DBAlarm> fetchAlarmModel() {
        List<DBAlarm> result = new ArrayList<>();
        try {
            List<ExpireNameFamilyID> twoColInfo = insuranceRepository.get2Column();
            List<Date> dates = insuranceRepository.getDates();
            Calendar comparable1 = Calendar.getInstance();
            Calendar comparable2 = Calendar.getInstance();
            DBAlarm information2 = new DBAlarm();
            information2.setMyDate(null);
            for (int i = 0; i < dates.size(); i++) {
                DBAlarm information = new DBAlarm();
                information.setMyDate(dates.get(i));
                int year2;
                int month2;
                int day2;
                if (dates.get(i) == null) {
                    year2 = -1;
                    month2 = -1;
                    day2 = -1;
                } else {
                    comparable2.setTime(dates.get(i));
                    year2 = comparable2.get(Calendar.YEAR);
                    month2 = comparable2.get(Calendar.MONTH);
                    day2 = comparable2.get(Calendar.DAY_OF_MONTH);
                }
                for (int j = 0; j < twoColInfo.size(); j++) {
                    int year1;
                    int month1;
                    int day1;
                    if (twoColInfo.get(j).getExpireDay() == null) {
                        year1 = -1;
                        month1 = -1;
                        day1 = -1;
                    } else {
                        comparable1.setTime(twoColInfo.get(j).getExpireDay());
                        year1 = comparable1.get(Calendar.YEAR);
                        month1 = comparable1.get(Calendar.MONTH);
                        day1 = comparable1.get(Calendar.DAY_OF_MONTH);
                    }
                    if (year1 == year2 && month1 == month2 && day1 == day2) {
                        if (dates.get(i) != null) {
                            if (dates.get(i).before(new Date())) {
                                information2.setMyDate(dates.get(i));
                                information2.addUser(new ShowingUser(twoColInfo.get(j).get_id(), twoColInfo.get(j).getName(), twoColInfo.get(j).getFamily()));
                            } else {

                                information.addUser(new ShowingUser(twoColInfo.get(j).get_id(), twoColInfo.get(j).getName(), twoColInfo.get(j).getFamily()));
                            }
                        } else {

                            information.addUser(new ShowingUser(twoColInfo.get(j).get_id(), twoColInfo.get(j).getName(), twoColInfo.get(j).getFamily()));
                        }
                    }
                }
                if (information.getUsers().size() != 0)
                {
                    int _id = preferenceManager.inc();
                    information.setId(_id);
                    _id++;
                    preferenceManager.setInc(_id);
                    result.add(information);

                }

            }
            if (information2.getUsers().size() != 0)
            {
                information2.setId(1);
                result.add(0, information2);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        alarmSetter.setGroupAlarm(result);
        return result;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "هشدار اتمام بیمه";
            String description = "بیمه هایی که رو به اتمام هستند";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}


