package com.example.reza.honarjo.View.insurance;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.reza.honarjo.Controller.DBInsurance.InsuranceViewModel;
import com.example.reza.honarjo.Controller.insuranceRecyclerAdapter.InsuranceRecyclerAdapter;
import com.example.reza.honarjo.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class InsuranceListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private InsuranceViewModel insuranceViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_list);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final InsuranceRecyclerAdapter adapter = new InsuranceRecyclerAdapter( this, item -> {
//            Intent myIntent = new Intent(getApplicationContext(), ShowUser.class);
//            myIntent.putExtra("User",item);
//            //startActivityForResult(myIntent,MEDICINE_WORK_ACTIVITY_REQUEST_CODE);
//            startActivity(myIntent);
        });
        recyclerView = findViewById(R.id.insurance_content_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        insuranceViewModel = ViewModelProviders.of(this).get(InsuranceViewModel.class);
        insuranceViewModel.getAllInsurances().observe(this, adapter::setInsurances);
        insuranceViewModel.getIsLoading().observe(this, isLoading -> {
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
