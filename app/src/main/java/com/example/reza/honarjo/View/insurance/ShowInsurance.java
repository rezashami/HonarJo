package com.example.reza.honarjo.View.insurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.reza.honarjo.Controller.userRecyclerAdapter.LocalRecyclerAdapter;
import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.R;
import com.example.reza.honarjo.View.user.ShowUser;

public class ShowInsurance extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LocalRecyclerAdapter adapter;
    private DBAlarm dbAlarm;

    //myIntent.putExtra("Insurance",item);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_insurance);
        Toolbar toolbar = findViewById(R.id.show_insurance_toolbar);
        setSupportActionBar(toolbar);
        setTitle("نمایش بیمه");
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            dbAlarm = (DBAlarm) b.getSerializable("Insurance");
            Log.e("Users: ", String.valueOf(dbAlarm.getUsers().size()));
        }


        adapter = new LocalRecyclerAdapter(this, item -> {
            Intent myIntent = new Intent(getApplicationContext(), ShowUser.class);
            myIntent.putExtra("User", item);
            //startActivityForResult(myIntent,MEDICINE_WORK_ACTIVITY_REQUEST_CODE);
            startActivity(myIntent);
        });
        adapter.setDbUsers(dbAlarm.getUsers());
        recyclerView = findViewById(R.id.show_insurance_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
