package com.example.reza.honarjo.View.logger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.reza.honarjo.Model.logger.DBLogger;
import com.example.reza.honarjo.R;

import saman.zamani.persiandate.PersianDate;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShowLogger extends AppCompatActivity {
    public static final int REPORT_DELETE_RESULT_CODE = 32;
    DBLogger logger;
    TextView txt_header, txt_body, txt_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_logger);
        Toolbar toolbar = findViewById(R.id.show_logger_toolbar);
        setSupportActionBar(toolbar);
        setTitle("مشاهده سابقه");
        txt_header = findViewById(R.id.logger_header_show);
        txt_body = findViewById(R.id.logger_body_show);
        txt_date = findViewById(R.id.logger_date_show);
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            logger = (DBLogger) b.getSerializable("Report");
            if (logger != null) {
                txt_header.setText(logger.getHeader());
                txt_body.setText(logger.getBody());
                PersianDate persianDate = new PersianDate(logger.getDate().getTime());
                txt_date.setText(persianDate.toString());
            }
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.show_logger_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_logger_delete:
                delete();
                return true;
            case R.id.show_logger_share:
                share();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void share() {
        String text = "سربرگ: " + logger.getHeader() + "\nبدنه: " + logger.getBody() + "\nتاریخ: " + logger.getDate();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void delete() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("حذف کاربر");
        alert.setMessage("آیا مطمئنید؟");
        alert.setPositiveButton("بلی", (dialogInterface, i) -> deleteLogger());
        alert.setNegativeButton("خیر", (dialog, which) -> dialog.cancel());
        alert.show();
    }

    private void deleteLogger() {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Removed item", logger);
        setResult(REPORT_DELETE_RESULT_CODE, replyIntent);
        finish();
    }

}

