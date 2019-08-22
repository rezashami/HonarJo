package com.example.reza.honarjo.View.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.reza.honarjo.R;

import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SettingActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar =  findViewById(R.id.setting_toolbar);
        setSupportActionBar(toolbar);
        setTitle("صفحه تنظیمات");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        findViewById(R.id.setting_exit_btn).setOnClickListener(v -> new AlertDialog.Builder(SettingActivity.this, R.style.MyAlertDialogStyle)
                .setTitle("خروج از برنامه")
                .setMessage("آیا مطمئن هستید؟")
                .setPositiveButton("بلی", (dialog, which) -> Toast.makeText(getApplicationContext(), "اتصال به اینترنت برقرار نمی‌باشد", Toast.LENGTH_SHORT).show())
                .setNegativeButton("خیر", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
