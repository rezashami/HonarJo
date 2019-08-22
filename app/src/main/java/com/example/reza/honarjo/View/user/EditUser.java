package com.example.reza.honarjo.View.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reza.honarjo.Controller.DBUser.UserRepository;
import com.example.reza.honarjo.Controller.prefrence.PreferenceManager;
import com.example.reza.honarjo.Controller.timeConverter.TimeConverter;
import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.R;

import java.util.Date;
import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.reza.honarjo.Controller.timeConverter.TimeConverter.getDayString;

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
    public static final String USER_EXTRA_REPLY = "com.example.reza.honarjo.View.user.USER_REPLY";
    public static final String DATE_EXTRA_REPLY = "com.example.reza.honarjo.View.user.DATE_REPLY";
    public final int EXP_CHANGE_CODE = 100;
    UserRepository userRepository;
    boolean EXPFlag = false;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = findViewById(R.id.edit_user_toolbar);
        setSupportActionBar(toolbar);
        setTitle("تغییر اطلاعات کاربر");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        preferenceManager = new PreferenceManager(getApplication());
        userRepository = new UserRepository(getApplication());
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            dbuSer = (DBUSer) b.getSerializable("User");
            if (dbuSer != null) {
                Log.e("DBUser", dbuSer.toString());
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
        if (!checkUI()) {
            Toast.makeText(this, "موراد را پر کنید", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            DBUSer user = getUser();
            user.set_id(dbuSer.get_id());
            userRepository.update(user);
            if (EXPFlag) {
                intent.putExtra(USER_EXTRA_REPLY, user);
                intent.putExtra(DATE_EXTRA_REPLY, dbuSer.getExpireDay());
                setResult(EXP_CHANGE_CODE, intent);
                finish();
            } else {
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("ویرایش اطلاعات");
        alert.setMessage("آیا تغییرات ذخیره شوند؟");
        alert.setPositiveButton("بلی", (dialog, which) -> save());
        alert.setNegativeButton("خیر", (dialog, which) -> {
            Intent replyIntent = new Intent();
            setResult(RESULT_CANCELED, replyIntent);
            finish();
        });
        alert.show();
    }


    private boolean checkUI() {
        boolean flag = true;
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("نام   را وارد کنید");
            flag = false;
        }
        if (TextUtils.isEmpty(family.getText())) {
            family.setError("نام خانوادگی را وارد کنید");
            flag = false;
        }
        if (TextUtils.isEmpty(phoneNumber.getText())) {
            phoneNumber.setError("تلفن را وارد کنید");
            flag = false;
        }
        if (TextUtils.isEmpty(registerDay.getText())) {
            registerDay.setError("روز را وارد کنید");
            flag = false;
        }
        if (TextUtils.isEmpty(registerMonth.getText())) {
            registerMonth.setError("ماه را وارد کنید");
            flag = false;
        }
        if (TextUtils.isEmpty(registerYear.getText())) {
            registerYear.setError("سال را وارد کنید");
            flag = false;
        }
        if (TextUtils.isEmpty(expireDay.getText())) {
            expireDay.setError(" روز را وارد کنید");
            flag = false;
        }
        if (TextUtils.isEmpty(expireMonth.getText())) {
            expireMonth.setError(" ماه را وارد کنید");
            flag = false;
        }
        if (TextUtils.isEmpty(expireYear.getText())) {
            expireYear.setError(" سال را وارد کنید");
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private DBUSer getUser() {
        String _name = name.getText().toString();
        String _family = family.getText().toString();
        String _phoneNumber = phoneNumber.getText().toString();
        boolean prv = privateCheck.isChecked();
        int _registerDay, _registerMonth, _registerYear,
                _expireDay, _expireMonth, _expireYear,
                _yellowDay = 0, _yellowMonth = 0, _yellowYear = 0,
                _orangeDay = 0, _orangeMonth = 0, _orangeYear = 0,
                _greenDay = 0, _greenMonth = 0, _greenYear = 0,
                _blueDay = 0, _blueMonth = 0, _blueYear = 0,
                _brownDay = 0, _brownMonth = 0, _brownYear = 0,
                _blackDay = 0, _blackMonth = 0, _blackYear = 0;
        _registerDay = Integer.parseInt(registerDay.getText().toString());
        _registerMonth = Integer.parseInt(registerMonth.getText().toString());
        _registerYear = Integer.parseInt(registerYear.getText().toString());
        _expireDay = Integer.parseInt(expireDay.getText().toString());
        _expireMonth = Integer.parseInt(expireMonth.getText().toString());
        _expireYear = Integer.parseInt(expireYear.getText().toString());
        if (!TextUtils.isEmpty(yellowDay.getText())) {
            _yellowDay = Integer.parseInt(yellowDay.getText().toString());
        }
        if (!TextUtils.isEmpty(yellowMonth.getText())) {
            _yellowMonth = Integer.parseInt(yellowMonth.getText().toString());
        }
        if (!TextUtils.isEmpty(yellowYear.getText())) {
            _yellowYear = Integer.parseInt(yellowYear.getText().toString());
        }
        if (!TextUtils.isEmpty(orangeDay.getText())) {
            _orangeDay = Integer.parseInt(orangeDay.getText().toString());
        }
        if (!TextUtils.isEmpty(orangeMonth.getText())) {
            _orangeMonth = Integer.parseInt(orangeMonth.getText().toString());
        }
        if (!TextUtils.isEmpty(orangeYear.getText())) {
            _orangeYear = Integer.parseInt(orangeYear.getText().toString());
        }
        if (!TextUtils.isEmpty(greenDay.getText())) {
            _greenDay = Integer.parseInt(greenDay.getText().toString());
        }
        if (!TextUtils.isEmpty(greenMonth.getText())) {
            _greenMonth = Integer.parseInt(greenMonth.getText().toString());
        }
        if (!TextUtils.isEmpty(greenYear.getText())) {
            _greenYear = Integer.parseInt(greenYear.getText().toString());
        }
        if (!TextUtils.isEmpty(blueDay.getText())) {
            _blueDay = Integer.parseInt(blueDay.getText().toString());
        }
        if (!TextUtils.isEmpty(blueMonth.getText())) {
            _blueMonth = Integer.parseInt(blueMonth.getText().toString());
        }
        if (!TextUtils.isEmpty(blueYear.getText())) {
            _blueYear = Integer.parseInt(blueYear.getText().toString());
        }
        if (!TextUtils.isEmpty(brownDay.getText())) {
            _brownDay = Integer.parseInt(brownDay.getText().toString());
        }
        if (!TextUtils.isEmpty(brownMonth.getText())) {
            _brownMonth = Integer.parseInt(brownMonth.getText().toString());
        }
        if (!TextUtils.isEmpty(brownYear.getText())) {
            _brownYear = Integer.parseInt(brownYear.getText().toString());
        }
        if (!TextUtils.isEmpty(blackDay.getText())) {
            _blackDay = Integer.parseInt(blackDay.getText().toString());
        }
        if (!TextUtils.isEmpty(blackMonth.getText())) {
            _blackMonth = Integer.parseInt(blackMonth.getText().toString());
        }
        if (!TextUtils.isEmpty(blackYear.getText())) {
            _blackYear = Integer.parseInt(blackYear.getText().toString());
        }
        int code =preferenceManager.getUserCode();
        DBUSer res = new DBUSer(code,_name, _family, _phoneNumber,
                ((_registerYear == 0) && (_registerMonth == 0) && (_registerDay == 0)) ?
                        null : getDate(_registerYear, _registerMonth, _registerDay),
                ((_expireYear == 0) && (_expireMonth == 0) && (_expireDay == 0)) ?
                        null :
                        getDate(_expireYear, _expireMonth, _expireDay),
                ((_yellowYear == 0) && (_yellowMonth == 0) && (_yellowDay == 0)) ?
                        null :
                        getDate(_yellowYear, _yellowMonth, _yellowDay),
                ((_orangeYear == 0) && (_orangeMonth == 0) && (_orangeDay == 0)) ?
                        null :
                        getDate(_orangeYear, _orangeMonth, _orangeDay),
                ((_greenYear == 0) && (_greenMonth == 0) && (_greenDay == 0)) ?
                        null :
                        getDate(_greenYear, _greenMonth, _greenDay),
                ((_blueYear == 0) && (_blueMonth == 0) && (_blueDay == 0)) ?
                        null :
                        getDate(_blueYear, _blueMonth, _blueDay),
                ((_brownYear == 0) && (_brownMonth == 0) && (_brownDay == 0)) ?
                        null :
                        getDate(_brownYear, _brownMonth, _brownDay),
                ((_blackYear == 0) && (_blackMonth == 0) && (_blackDay == 0)) ?
                        null :
                        getDate(_blackYear, _blackMonth, _blackDay),
                prv, code);
        if (dbuSer.getExpireDay() != res.getExpireDay())
            EXPFlag = true;
        code++;
        preferenceManager.setUserCode(code);
        return res;
    }

    private Date getDate(Integer item1, Integer item2, Integer item3) {
        return TimeConverter.getGEOTime(item1, item2, item3);
    }
}
