package com.example.reza.honarjo.View.user;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reza.honarjo.Controller.DBUser.UserViewModel;
import com.example.reza.honarjo.Controller.api.api;
import com.example.reza.honarjo.Controller.api.appClient;
import com.example.reza.honarjo.Controller.db.DaoAccess;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Controller.prefrence.PreferenceManager;
import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.Model.users.DeleteId;
import com.example.reza.honarjo.Model.users.ShowingUser;
import com.example.reza.honarjo.R;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static java.lang.String.valueOf;

public class ShowUser extends AppCompatActivity {

    private static final int EDIT_USER_REQUEST_CODE = 1;
    DBUSer dbuSer;
    ShowingUser showingUser;
    DaoAccess AppDao;
    TextView nameFamily, phoneNumber, registerDay, expireDay, yellowDay, orangeDay, greenDay, blueDay, brownDay, blackDay, privateCheck;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager db = DatabaseManager.getDatabase(getApplication());
        AppDao = db.daoAccess();
        preferenceManager = new PreferenceManager(getApplicationContext());
        setContentView(R.layout.activity_show_user);
        Toolbar toolbar = findViewById(R.id.show_user_toolbar);
        setSupportActionBar(toolbar);
        setTitle("اطلاعات کاربری");
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            showingUser = (ShowingUser) b.getSerializable("User");
            try {
                dbuSer = new GetAsync(showingUser.get_id(), AppDao).execute().get();
                renderUI();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static class GetAsync extends AsyncTask<Void, Void, DBUSer> {
        final String ID;
        final DaoAccess AppDao;

        GetAsync(String id, DaoAccess appDao) {
            ID = id;
            AppDao = appDao;
        }

        @Override
        protected DBUSer doInBackground(Void... voids) {
            return AppDao.getOneUser(ID);
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
        registerDay.setText(getDayString(dbuSer.getRegisterDay()));
        expireDay = findViewById(R.id.show_user_expire_day);
        expireDay.setText(getDayString(dbuSer.getExpireDay()));
        yellowDay = findViewById(R.id.show_user_yellow_day);
        yellowDay.setText(getDayString(dbuSer.getYellowDay()));
        orangeDay = findViewById(R.id.show_user_orange_day);
        orangeDay.setText(getDayString(dbuSer.getOrangeDay()));
        greenDay = findViewById(R.id.show_user_green_day);
        greenDay.setText(getDayString(dbuSer.getGreenDay()));
        blueDay = findViewById(R.id.show_user_blue_day);
        blueDay.setText(getDayString(dbuSer.getBlueDay()));
        brownDay = findViewById(R.id.show_user_brown_day);
        brownDay.setText(getDayString(dbuSer.getBrownDay()));
        blackDay = findViewById(R.id.show_user_black_day);
        blackDay.setText(getDayString(dbuSer.getBlackDay()));
        privateCheck = findViewById(R.id.show_user_private_check);
        privateCheck.setText(dbuSer.isPrivateCheck() ? "بله" : "خیر");
    }

    private String getDayString(List<Integer> input) {
        if (input == null)
            return "ثبت نشده";
        if (input.size() == 0)
            return "ثبت نشده";
        Log.e("SHowUSer", input.toString());
        String month = input.get(1) < 10 ? "0" + input.get(1) : input.get(1).toString();
        String day = input.get(0) < 10 ? "0" + input.get(0) : input.get(0).toString();
        return toPersianNumber(input.get(2).toString() + " - " + month + " - " + day);
    }

    public String toPersianNumber(String text) {
        String[] persianNumbers = new String[]{"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};
        if (text.length() == 0) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(valueOf(c));
                out.append(persianNumbers[number]);
            } else {
                out.append(c);
            }
        }

        return out.toString();
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

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    private void delete() {
        if (!isInternetAvailable()) {
            Toast.makeText(this, "اتصال به اینترنت برقرار نمی‌باشد", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("حذف کاربر");
        alert.setMessage("آیا مطمئنید؟");
        alert.setPositiveButton("بلی", (dialogInterface, i) -> {
            Intent reply = new Intent();
            api apiInterface = appClient.getInstance().create(api.class);
            Call<String> call = apiInterface.deleteUser(preferenceManager.getToken(), new DeleteId(dbuSer.get_id()));
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if (response.code() == 200 && response.body() != null) {
                        UserViewModel userViewModel = ViewModelProviders.of(ShowUser.this).get(UserViewModel.class);
                        userViewModel.remove(dbuSer);
                        Log.e("Response 200", response.toString());
                        setResult(RESULT_OK, reply);
                        finish();
                    } else {
                        Log.e("Response ---", response.toString());
                        Toast.makeText(getApplicationContext(), "خطا در گرفتن پاسخ", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED, reply);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
                    Log.e("Response", t.getMessage());
                    setResult(RESULT_CANCELED, reply);
                    finish();
                }
            });
        });
        alert.setNegativeButton("خیر", (dialog, which) -> dialog.cancel());
        alert.show();
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
