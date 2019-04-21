package com.example.reza.honarjo.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.reza.honarjo.Controller.DBUser.UserViewModel;
import com.example.reza.honarjo.Controller.recyclerAdapter.LocalRecyclerAdapter;
import com.example.reza.honarjo.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class UserListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());


        final LocalRecyclerAdapter adapter = new LocalRecyclerAdapter( this, item -> {
            Intent myIntent = new Intent(getApplicationContext(), ShowUser.class);
            myIntent.putExtra("User",item);
            //startActivityForResult(myIntent,MEDICINE_WORK_ACTIVITY_REQUEST_CODE);
            startActivity(myIntent);
        });
        recyclerView = findViewById(R.id.user_content_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllWords().observe(this, adapter::setDbUsers);
        userViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading!= null)
                if (isLoading)
                    Toast.makeText(getApplicationContext(), "منتظر بمانید...", Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
