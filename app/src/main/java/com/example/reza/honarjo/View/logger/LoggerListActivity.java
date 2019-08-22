package com.example.reza.honarjo.View.logger;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.reza.honarjo.Controller.DBLogger.LoggerViewModel;
import com.example.reza.honarjo.Controller.loggerRecyclerAdapter.LoggerRecyclerAdapter;
import com.example.reza.honarjo.Model.logger.DBLogger;
import com.example.reza.honarjo.R;

import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoggerListActivity extends AppCompatActivity {
    public static final int SHOW_REPORT_REQUEST_CODE = 32;
    private LoggerViewModel loggerViewModel;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar =  findViewById(R.id.logger_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.logger_content_recycler);
        final LoggerRecyclerAdapter adapter = new LoggerRecyclerAdapter( this, item -> {
            Intent myIntent = new Intent(getApplicationContext(), ShowLogger.class);
            Bundle b = new Bundle();
            b.putSerializable("Report",item);
            myIntent.putExtras(b);
            startActivityForResult(myIntent,SHOW_REPORT_REQUEST_CODE);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loggerViewModel = ViewModelProviders.of(this).get(LoggerViewModel.class);
        loggerViewModel.getAllReports().observe(this, adapter::setLogs);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SHOW_REPORT_REQUEST_CODE && resultCode == ShowLogger.REPORT_DELETE_RESULT_CODE)
        {
            DBLogger report = (DBLogger) data.getSerializableExtra("Removed item");
            loggerViewModel.remove(report);
            Toast.makeText(getApplicationContext(), "سابقه پاک شد!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
