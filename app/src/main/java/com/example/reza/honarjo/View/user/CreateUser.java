package com.example.reza.honarjo.View.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reza.honarjo.Controller.DBUser.UserRepository;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Controller.prefrence.PreferenceManager;
import com.example.reza.honarjo.Controller.timeConverter.TimeConverter;
import com.example.reza.honarjo.Model.logger.DBLogger;
import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.R;

import java.util.Date;

import saman.zamani.persiandate.PersianDate;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CreateUser extends AppCompatActivity {

    public static final String USER_CREATE_REPLY = "com.example.reza.honarjo.View.user.USER_CREATE_REPLY";
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
    private UserRepository userRepository;

    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Toolbar toolbar = findViewById(R.id.create_user_toolbar);
        setSupportActionBar(toolbar);
        setTitle("افزودن کاربر");
        preferenceManager = new PreferenceManager(getApplicationContext());
        name = findViewById(R.id.create_user_name);
        family = findViewById(R.id.create_user_family);
        phoneNumber = findViewById(R.id.create_user_phone_number);
        privateCheck = findViewById(R.id.create_user_private);
        registerDay = findViewById(R.id.create_user_register_day);
        expireDay = findViewById(R.id.create_user_expire_day);
        yellowDay = findViewById(R.id.create_user_yellow_day);
        orangeDay = findViewById(R.id.create_user_orange_day);
        greenDay = findViewById(R.id.create_user_green_day);
        blueDay = findViewById(R.id.create_user_blue_day);
        brownDay = findViewById(R.id.create_user_brown_day);
        blackDay = findViewById(R.id.create_user_black_day);
        registerMonth = findViewById(R.id.create_user_register_month);
        expireMonth = findViewById(R.id.create_user_expire_month);
        yellowMonth = findViewById(R.id.create_user_yellow_month);
        orangeMonth = findViewById(R.id.create_user_orange_month);
        greenMonth = findViewById(R.id.create_user_green_month);
        blueMonth = findViewById(R.id.create_user_blue_month);
        brownMonth = findViewById(R.id.create_user_brown_month);
        blackMonth = findViewById(R.id.create_user_black_month);
        registerYear = findViewById(R.id.create_user_register_year);
        expireYear = findViewById(R.id.create_user_expire_year);
        yellowYear = findViewById(R.id.create_user_yellow_year);
        orangeYear = findViewById(R.id.create_user_orange_year);
        greenYear = findViewById(R.id.create_user_green_year);
        blueYear = findViewById(R.id.create_user_blue_year);
        brownYear = findViewById(R.id.create_user_brown_year);
        blackYear = findViewById(R.id.create_user_black_year);
        userRepository = new UserRepository(getApplication());
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
            create();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void create() {
        Intent intent = new Intent();
        if (!checkUI()) {
            Toast.makeText(this, "موراد را پر کنید", Toast.LENGTH_SHORT).show();
        } else {
            DBUSer user = getUser();
            userRepository.insert(user);
            intent.putExtra(USER_CREATE_REPLY,user);
            new Thread(() -> {
                DBLogger report = new DBLogger();
                PersianDate persianDate = new PersianDate(new Date().getTime());
                String header = "افزودن کاربر، در تاریخ: " + persianDate.toString();
                report.setHeader(header);
                report.setBody(user.toString());
                report.setDate(new Date());
                DatabaseManager databaseHelper = DatabaseManager.getDatabase(getApplicationContext());
                databaseHelper.daoAccess().insertLog(report);
            }).start();
            setResult(RESULT_OK, intent);
            finish();
        }
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
//        } else if (TextUtils.isEmpty(yellowDay.getText())) {
//            yellowDay.setError(" روز را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(yellowMonth.getText())) {
//            yellowMonth.setError(" ماه را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(yellowYear.getText())) {
//            yellowYear.setError(" سال را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(orangeDay.getText())) {
//            orangeDay.setError(" روز را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(orangeMonth.getText())) {
//            orangeMonth.setError(" ماه را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(orangeYear.getText())) {
//            orangeYear.setError(" سال را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(greenDay.getText())) {
//            greenDay.setError(" روز را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(greenMonth.getText())) {
//            greenMonth.setError(" ماه را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(greenYear.getText())) {
//            greenYear.setError(" سال را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(blueDay.getText())) {
//            blueDay.setError(" روز را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(blueMonth.getText())) {
//            blueMonth.setError(" ماه را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(blueYear.getText())) {
//            blueYear.setError(" سال را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(brownDay.getText())) {
//            brownDay.setError(" روز را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(brownMonth.getText())) {
//            brownMonth.setError(" ماه را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(brownYear.getText())) {
//            brownYear.setError(" سال را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(blackDay.getText())) {
//            blackDay.setError(" روز را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(blackMonth.getText())) {
//            blackMonth.setError("نام را وارد کنید");
//            flag = false;
//        } else if (TextUtils.isEmpty(blackYear.getText())) {
//            blackYear.setError("نام را وارد کنید");
//            flag = false;
//        }
        return flag;
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
        return new DBUSer(_name, _family, _phoneNumber,
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
                prv, preferenceManager.getUserCode()); //code must be edit
    }

    private Date getDate(Integer item1, Integer item2, Integer item3) {
        return TimeConverter.getGEOTime(item1, item2, item3);
    }

}
