package com.example.reza.honarjo.View.user;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.reza.honarjo.Controller.DBUser.UserViewModel;
import com.example.reza.honarjo.Controller.userRecyclerAdapter.LocalRecyclerAdapter;
import com.example.reza.honarjo.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class UserListActivity extends AppCompatActivity {

    private static final int CREATE_USER_CODE = 1;

    RecyclerView recyclerView;
    private UserViewModel userViewModel;
    private FloatingActionButton fab;

    private boolean isSearchOpen;
    private LocalRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent myIntent = new Intent(getApplicationContext(), CreateUser.class);
            startActivityForResult(myIntent,CREATE_USER_CODE);
        });

        adapter = new LocalRecyclerAdapter(this, item -> {
            Intent myIntent = new Intent(getApplicationContext(), ShowUser.class);
            myIntent.putExtra("User", item);
            //startActivityForResult(myIntent,MEDICINE_WORK_ACTIVITY_REQUEST_CODE);
            startActivity(myIntent);
        });

        recyclerView = findViewById(R.id.user_content_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllWords().observe(this, adapter::setDbUsers);
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
                userViewModel.getAllWords().observe(UserListActivity.this, adapter::setDbUsers);
                isSearchOpen = true;
                fab.hide();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                userViewModel.getAllWords().observe(UserListActivity.this, adapter::setDbUsers);
                fab.show();
                isSearchOpen = false;
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
                searchText = "%" + searchText + "%";
                userViewModel.getUsersByName(searchText).observe(UserListActivity.this, adapter::setDbUsers);
            }
        });
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_USER_CODE && resultCode == RESULT_OK)
        {
            Toast.makeText(this, "کاربر با موفقیت افزوده شد", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == CREATE_USER_CODE && resultCode == RESULT_CANCELED)
        {
            Toast.makeText(this, "کاربری افزوده نشد!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (isSearchOpen) {
            fab.show();
            isSearchOpen = false;
        } else {
            super.onBackPressed();
        }
    }
}
