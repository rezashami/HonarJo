package com.example.reza.honarjo.View.insurance;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.reza.honarjo.Controller.userRecyclerAdapter.LocalRecyclerAdapter;
import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.Model.users.ShowingUser;
import com.example.reza.honarjo.R;
import com.example.reza.honarjo.View.user.ShowUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.reza.honarjo.Controller.timeConverter.TimeConverter.getPersianDashedTime;

public class ShowInsurance extends AppCompatActivity {
    private DBAlarm dbAlarm;
    private LocalRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_insurance);
        Toolbar toolbar = findViewById(R.id.show_insurance_toolbar);
        setSupportActionBar(toolbar);
        setTitle("نمایش بیمه");
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            dbAlarm = (DBAlarm) b.getSerializable("Insurance");
        }
        TextView txt = findViewById(R.id.show_insurance_expireDay);

        if (dbAlarm.getMyDate().before(new Date())) {
            txt.setText("منقضی شده");
        } else {
            txt.setText(getPersianDashedTime(dbAlarm.getMyDate()));
        }
        adapter = new LocalRecyclerAdapter(this, item -> {
            Intent myIntent = new Intent(getApplicationContext(), ShowUser.class);
            myIntent.putExtra("User", item);
            startActivity(myIntent);
        });
        adapter.setDbUsers(dbAlarm.getUsers());
        RecyclerView recyclerView = findViewById(R.id.show_insurance_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.user_list_toolbar, menu);
        MenuItem search = menu.findItem(R.id.user_list_search);
        search.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                adapter.setDbUsers(dbAlarm.getUsers());
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                adapter.setDbUsers(dbAlarm.getUsers());
                return true;
            }
        });
        SearchView searchView = (SearchView) search.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("جست و جو کاربر");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getDealsFromDb(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getDealsFromDb(s);
                return true;
            }

            private void getDealsFromDb(String searchText) {
                String regex = ".*" + searchText + ".*";
                List<ShowingUser> result = new ArrayList<>();
                for (ShowingUser item : dbAlarm.getUsers()) {
                    if (item.getFamily().matches(regex) || item.getName().matches(regex)) {
                        result.add(item);
                    }
                }
                adapter.setDbUsers(result);
            }
        });
        return true;
    }
}
