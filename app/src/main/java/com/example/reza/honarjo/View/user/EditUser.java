package com.example.reza.honarjo.View.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.R;

import java.util.Calendar;
import java.util.Date;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditUser extends AppCompatActivity {

    DBUSer dbuSer;
    EditText name, family, phoneNumber,
            registerDay, registerMonth, registerYear,
            expireDay, expireMonth, expireYear,
            yellowDay, yellowMonth, yellowYear,
            orangeDay, orangeMonth, orangeYear,
            greenDay, greenMonth, greenYear,
            blueDay, blueMonth, blueYear,
            brownDay, brownMonth, brownYear,
            blackDay, blackMonth, blackYear;
    CheckBox privateCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = findViewById(R.id.edit_user_toolbar);
        setSupportActionBar(toolbar);
        setTitle("تغییر اطلاعات کاربر");
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            dbuSer = (DBUSer) b.getSerializable("User");
            if (dbuSer != null) {
                Log.e("DBUSER", dbuSer.toString());
            }
        }
        renderUI();
    }

    @SuppressLint("SetTextI18n")
    private void renderUI() {
        name = findViewById(R.id.edit_user_name);
        name.setText(dbuSer.getName());
        family = findViewById(R.id.edit_user_family);
        family.setText(dbuSer.getFamily());
        phoneNumber = findViewById(R.id.edit_user_phone_number);
        phoneNumber.setText(dbuSer.getPhoneNumber());
        privateCheck = findViewById(R.id.edit_user_private);
        privateCheck.setChecked(dbuSer.isPrivateCheck());

        registerDay = findViewById(R.id.edit_user_register_day);
        registerDay.setText(getDayString(dbuSer.getRegisterDay(), 0));
        expireDay = findViewById(R.id.edit_user_expire_day);
        expireDay.setText(getDayString(dbuSer.getExpireDay(), 0));
        yellowDay = findViewById(R.id.edit_user_yellow_day);
        yellowDay.setText(getDayString(dbuSer.getYellowDay(), 0));
        orangeDay = findViewById(R.id.edit_user_orange_day);
        orangeDay.setText(getDayString(dbuSer.getOrangeDay(), 0));
        greenDay = findViewById(R.id.edit_user_green_day);
        greenDay.setText(getDayString(dbuSer.getGreenDay(), 0));
        blueDay = findViewById(R.id.edit_user_blue_day);
        blueDay.setText(getDayString(dbuSer.getBlueDay(), 0));
        brownDay = findViewById(R.id.edit_user_brown_day);
        brownDay.setText(getDayString(dbuSer.getBrownDay(), 0));
        blackDay = findViewById(R.id.edit_user_black_day);
        blackDay.setText(getDayString(dbuSer.getBlackDay(), 0));

        registerMonth = findViewById(R.id.edit_user_register_month);
        registerMonth.setText(getDayString(dbuSer.getRegisterDay(), 1));
        expireMonth = findViewById(R.id.edit_user_expire_month);
        expireMonth.setText(getDayString(dbuSer.getExpireDay(), 1));
        yellowMonth = findViewById(R.id.edit_user_yellow_month);
        yellowMonth.setText(getDayString(dbuSer.getYellowDay(), 1));
        orangeMonth = findViewById(R.id.edit_user_orange_month);
        orangeMonth.setText(getDayString(dbuSer.getOrangeDay(), 1));
        greenMonth = findViewById(R.id.edit_user_green_month);
        greenMonth.setText(getDayString(dbuSer.getGreenDay(), 1));
        blueMonth = findViewById(R.id.edit_user_blue_month);
        blueMonth.setText(getDayString(dbuSer.getBlueDay(), 1));
        brownMonth = findViewById(R.id.edit_user_brown_month);
        brownMonth.setText(getDayString(dbuSer.getBrownDay(), 1));
        blackMonth = findViewById(R.id.edit_user_black_month);
        blackMonth.setText(getDayString(dbuSer.getBlackDay(), 1));

        registerYear = findViewById(R.id.edit_user_register_year);
        registerYear.setText(getDayString(dbuSer.getRegisterDay(), 2));
        expireYear = findViewById(R.id.edit_user_expire_year);
        expireYear.setText(getDayString(dbuSer.getExpireDay(), 2));
        yellowYear = findViewById(R.id.edit_user_yellow_year);
        yellowYear.setText(getDayString(dbuSer.getYellowDay(), 2));
        orangeYear = findViewById(R.id.edit_user_orange_year);
        orangeYear.setText(getDayString(dbuSer.getOrangeDay(), 2));
        greenYear = findViewById(R.id.edit_user_green_year);
        greenYear.setText(getDayString(dbuSer.getGreenDay(), 2));
        blueYear = findViewById(R.id.edit_user_blue_year);
        blueYear.setText(getDayString(dbuSer.getBlueDay(), 2));
        brownYear = findViewById(R.id.edit_user_brown_year);
        brownYear.setText(getDayString(dbuSer.getBrownDay(), 2));
        blackYear = findViewById(R.id.edit_user_black_year);
        blackYear.setText(getDayString(dbuSer.getBlackDay(), 2));
    }

    @NonNull
    private String getDayString(Date input, int number) {
        String result =" - ";
        Calendar cal_alarm = Calendar.getInstance();
        cal_alarm.setTime(input);
        if (input == null)
            return result;
        if(number == 0)
        {
            result  = String.valueOf(cal_alarm.get(Calendar.YEAR));
        }
        else if(number == 1)
        {
            result  = String.valueOf(cal_alarm.get(Calendar.MONTH));
        }
        else if(number == 2)
        {
            result  = String.valueOf(cal_alarm.get(Calendar.DAY_OF_MONTH));
        }
        return result;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_user_save_user) {
            showDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void save() {
       //save to db
    }

    void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("ویرایش اطلاعات");
        alert.setMessage("آیا تغییرات ذخیره شوند؟");
        alert.setPositiveButton("بلی", (dialog, which) -> save());
        alert.setNegativeButton("خیر", (dialog, which) -> dialog.cancel());
        alert.show();
    }
}
