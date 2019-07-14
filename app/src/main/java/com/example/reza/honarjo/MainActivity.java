package com.example.reza.honarjo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.reza.honarjo.Controller.DBInsurance.InsuranceRepository;
import com.example.reza.honarjo.Controller.prefrence.PreferenceManager;
import com.example.reza.honarjo.Model.alarm.ServerReplyInsurance;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private ServerReplyInsurance alarmInformation;
    PreferenceManager preferenceManager;
    InsuranceRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.content_main).setVisibility(View.VISIBLE);
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }
        }, 2000);
        preferenceManager = new PreferenceManager(getApplicationContext());
        mRepository = new InsuranceRepository(getApplication());

//        if (!preferenceManager.FirstLaunch()) {
//            fetchLocally();
//        } else {
//            fetchOnline();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


//    private void setAlarm(int dayOfWeek, int Id) {
//        Calendar cal_alarm = Calendar.getInstance();
//
//        cal_alarm.set(Calendar.DAY_OF_WEEK, dayOfWeek);
//        cal_alarm.set(Calendar.HOUR_OF_DAY, alarm.getHour());
//        cal_alarm.set(Calendar.MINUTE, alarm.getMinute());
//        cal_alarm.set(Calendar.SECOND, 0);
//        cal_alarm.set(Calendar.MILLISECOND, 0);
//        if (cal_alarm.before(Calendar.getInstance())) {
//            cal_alarm.add(Calendar.DAY_OF_WEEK, 7);
//        }
//        Log.e("Alarm date", "hour: " + valueOf(alarm.getHour()) + " minute: " + valueOf(alarm.getMinute()));
//        Log.e("Time in millis ", new Date(cal_alarm.getTimeInMillis()).toString());
//        Intent intent = new Intent(AlarmList.this, AlarmReceiver.class);
//        Bundle b = new Bundle();
//        b.putSerializable("Alarm", alarm);
//        b.putInt("SNOOZE_COUNTER", 0);
//        intent.putExtras(b);
//        PendingIntent pendingIntent;
//        final int _id = (Id * 7) - dayOfWeek;
//        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), _id, intent, 0);
//        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        Log.e("Configured Alarm ", "Set to: " + cal_alarm.getTime().toString() + " and interval is: 7");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (alarmManager != null) {
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
//            }
//        } else {
//            if (alarmManager != null) {
//                alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
//            }
//        }
//        //7 * 24 * 60 * 60 * 1000
//    }

//    public void fetchOnline() {
//        api apiInterface = appClient.getInstance().create(api.class);
//        Call<List<User>> call = apiInterface.getAllUsers(preferenceManager.getToken());
//        Call<ServerReplyInsurance> myCall = apiInterface.getInsurancesAlarm(preferenceManager.getToken());
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                assert response.body() != null;
//                users = response.body();
//                MainRecyclerAdapter adapter = new MainRecyclerAdapter(users);
//                recyclerView.setAdapter(adapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                UserViewModel userViewModel = ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);
//                for (User item : users) {
//                    userViewModel.insert(new DBUSer(item));
//                }
//                preferenceManager.setFirstTimeLaunch(false);
//                myCall.enqueue(new Callback<ServerReplyInsurance>() {
//                    @Override
//                    public void onResponse(Call<ServerReplyInsurance> call, Response<ServerReplyInsurance> response) {
//                        assert response.body() != null;
//                        alarmInformation = response.body();
//                        List<AlarmInformation> pass = alarmInformation.getPass();
//                        List<AlarmInformation> notPass = alarmInformation.getNotPass();
//                        List<ShowingUser> passUsers = new ArrayList<>();
//
//                        for (AlarmInformation item : pass) {
//                            passUsers.addAll(item.getUserId());
//                        }
//                        mRepository.insert(new DBAlarm(passUsers,new MyDate(0,0,0)));
//                        for (AlarmInformation item : notPass) {
//                            mRepository.insert(new DBAlarm(item.getUserId(),item.getMyDate()));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ServerReplyInsurance> call, Throwable t) {
//                        Log.e("Tag error nested", t.toString());
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Log.e("Tag error", t.toString());
//            }
//        });
//    }
}
