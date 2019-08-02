package com.example.reza.honarjo.View.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reza.honarjo.Controller.db.DaoAccess;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.Model.users.ShowingUser;
import com.example.reza.honarjo.R;

import java.util.concurrent.ExecutionException;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.reza.honarjo.Controller.timeConverter.TimeConverter.getPersianDashedTime;
import static com.example.reza.honarjo.Controller.timeConverter.TimeConverter.toPersianNumber;

public class ShowUser extends AppCompatActivity {

    private static final int EDIT_USER_REQUEST_CODE = 1;
    DBUSer dbuSer;
    ShowingUser showingUser;
    DaoAccess AppDao;
    TextView nameFamily, phoneNumber, registerDay, expireDay, yellowDay, orangeDay, greenDay, blueDay, brownDay, blackDay, privateCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager db = DatabaseManager.getDatabase(getApplication());
        AppDao = db.daoAccess();
        setContentView(R.layout.activity_show_user);
        Toolbar toolbar = findViewById(R.id.show_user_toolbar);
        setSupportActionBar(toolbar);
        setTitle("اطلاعات کاربری");
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            showingUser = (ShowingUser) b.getSerializable("User");
            try {
                if (showingUser != null) {
                    dbuSer = new GetAsync(showingUser.get_id(), AppDao).execute().get();
                }
                renderUI();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @SuppressLint("SetTextI18n")
    public void renderUI() {
        assert dbuSer != null;
        nameFamily = findViewById(R.id.show_user_name_family);
        nameFamily.setText(dbuSer.getName() + " " + dbuSer.getFamily());
        phoneNumber = findViewById(R.id.show_user_phone_number);
        phoneNumber.setText(toPersianNumber(dbuSer.getPhoneNumber()));
        registerDay = findViewById(R.id.show_user_register_day);
        registerDay.setText(getPersianDashedTime(dbuSer.getRegisterDay()));
        expireDay = findViewById(R.id.show_user_expire_day);
        expireDay.setText(getPersianDashedTime(dbuSer.getExpireDay()));
        yellowDay = findViewById(R.id.show_user_yellow_day);
        yellowDay.setText(getPersianDashedTime(dbuSer.getYellowDay()));
        orangeDay = findViewById(R.id.show_user_orange_day);
        orangeDay.setText(getPersianDashedTime(dbuSer.getOrangeDay()));
        greenDay = findViewById(R.id.show_user_green_day);
        greenDay.setText(getPersianDashedTime(dbuSer.getGreenDay()));
        blueDay = findViewById(R.id.show_user_blue_day);
        blueDay.setText(getPersianDashedTime(dbuSer.getBlueDay()));
        brownDay = findViewById(R.id.show_user_brown_day);
        brownDay.setText(getPersianDashedTime(dbuSer.getBrownDay()));
        blackDay = findViewById(R.id.show_user_black_day);
        blackDay.setText(getPersianDashedTime(dbuSer.getBlackDay()));
        privateCheck = findViewById(R.id.show_user_private_check);
        privateCheck.setText(dbuSer.isPrivateCheck() ? "بله" : "خیر");
    }

    private void delete() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("حذف کاربر");
        alert.setMessage("آیا مطمئنید؟");
        alert.setPositiveButton("بلی", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "تغییری اعمال نشد!", Toast.LENGTH_SHORT).show());
        alert.setNegativeButton("خیر", (dialog, which) -> dialog.cancel());
        alert.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.show_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_user_edit_user:
                edit();
                return true;
            case R.id.delete_user_edit_user:
                delete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private static class GetAsync extends AsyncTask<Void, Void, DBUSer> {
        final Integer ID;
        final DaoAccess AppDao;

        GetAsync(Integer id, DaoAccess appDao) {
            ID = id;
            AppDao = appDao;
        }

        @Override
        protected DBUSer doInBackground(Void... voids) {
            return AppDao.getOneUser(ID);
        }
    }

    private void edit() {
        Intent myIntent = new Intent(getApplicationContext(), EditUser.class);
        myIntent.putExtra("User", dbuSer);
        startActivityForResult(myIntent, EDIT_USER_REQUEST_CODE);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_USER_REQUEST_CODE && resultCode == RESULT_OK) {
            //save and edit user online
        } else if (requestCode == EDIT_USER_REQUEST_CODE && resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "تغییری اعمال نشد!", Toast.LENGTH_SHORT).show();
        }
    }
}
