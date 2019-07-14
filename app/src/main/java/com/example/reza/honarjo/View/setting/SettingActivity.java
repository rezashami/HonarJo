package com.example.reza.honarjo.View.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.reza.honarjo.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar =  findViewById(R.id.setting_toolbar);
        setSupportActionBar(toolbar);
        setTitle("صفحه تنظیمات");
        findViewById(R.id.setting_exit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SettingActivity.this, R.style.MyAlertDialogStyle)
                        .setTitle("خروج از برنامه")
                        .setMessage("آیا مطمئن هستید؟")
                        .setPositiveButton("بلی", (dialog, which) -> Toast.makeText(getApplicationContext(),"خارج شد!", Toast.LENGTH_LONG).show())
                        .setNegativeButton("خیر", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
