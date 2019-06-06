package com.example.reza.honarjo;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.reza.honarjo.Controller.DBInsurance.InsuranceRepository;
import com.example.reza.honarjo.Controller.DBInsurance.InsuranceViewModel;
import com.example.reza.honarjo.Controller.DBUser.UserViewModel;
import com.example.reza.honarjo.Controller.api.api;
import com.example.reza.honarjo.Controller.api.appClient;
import com.example.reza.honarjo.Controller.prefrence.PreferenceManager;
import com.example.reza.honarjo.Controller.userRecyclerAdapter.LocalRecyclerAdapter;
import com.example.reza.honarjo.Controller.userRecyclerAdapter.OnlineRecyclerAdapter;
import com.example.reza.honarjo.Model.alarm.AlarmInformation;
import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.Model.users.ShowingUser;
import com.example.reza.honarjo.Model.users.User;
import com.example.reza.honarjo.View.insurance.InsuranceListActivity;
import com.example.reza.honarjo.View.user.ShowUser;
import com.example.reza.honarjo.View.user.UserListActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    RecyclerView recyclerView;
    private List<User> users;
    PreferenceManager preferenceManager;
    InsuranceRepository mRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferenceManager = new PreferenceManager(getApplicationContext());
        mRepository = new InsuranceRepository(getApplication());
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationOnClickListener(v -> {
            if (drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.closeDrawer(GravityCompat.END);
            } else {
                drawer.openDrawer(GravityCompat.END);
            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        recyclerView = findViewById(R.id.today_recycler);
        if (!preferenceManager.FirstLaunch()) {
            Log.e("not FirstLunch", "fetch locally");
            fetchLocally();
        } else {
            fetchOnline();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_user) {
            Intent intent = new Intent(MainActivity.this, UserListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_bime) {
            Intent intent = new Intent(MainActivity.this, InsuranceListActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public void setAlarm() {
    }

    public void fetchOnline() {
        api apiInterface = appClient.getInstance().create(api.class);
        Call<List<User>> call = apiInterface.getAllUsers(preferenceManager.getToken());
        Call <List<AlarmInformation>> myCall = apiInterface.getInsurancesAlarm(preferenceManager.getToken());
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                assert response.body() != null;
                Log.e("MES", response.body().toString());
                users = response.body();
                OnlineRecyclerAdapter adapter = new OnlineRecyclerAdapter(users);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                UserViewModel userViewModel = ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);
                for (User item : users) {
                    userViewModel.insert(new DBUSer(item));
                }
                preferenceManager.setFirstTimeLaunch(false);
                myCall.enqueue(new Callback<List<AlarmInformation>>() {
                    @Override
                    public void onResponse(Call<List<AlarmInformation>> call, Response<List<AlarmInformation>> response) {
                        assert response.body() != null;
                        List <AlarmInformation> alarmInformation = response.body();
                        for (AlarmInformation item : alarmInformation) {
                            mRepository.insert(new DBAlarm(item));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<AlarmInformation>> call, Throwable t) {
                        Log.e("Tag error nested", t.toString());
                    }
                });
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Tag error", t.toString());
            }
        });
    }

    private void fetchLocally() {
        final LocalRecyclerAdapter adapter = new LocalRecyclerAdapter(this, item -> {
            Intent myIntent = new Intent(getApplicationContext(), ShowUser.class);
            myIntent.putExtra("User", item);
            //startActivityForResult(myIntent,MEDICINE_WORK_ACTIVITY_REQUEST_CODE);
            startActivity(myIntent);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserViewModel userViewModel = ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);
        //userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllWords().observe(this, adapter::setDbUsers);
    }
}
