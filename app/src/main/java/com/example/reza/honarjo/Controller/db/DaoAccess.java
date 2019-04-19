package com.example.reza.honarjo.Controller.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.reza.honarjo.Model.DBUSer;

import java.util.List;

@Dao
public interface DaoAccess {
    @Query("SELECT * FROM users")
    LiveData<List<DBUSer>> getAllUsers();
    @Query("SELECT * FROM users WHERE name = :name")
    DBUSer getMedicine(String name);

    @Insert
    void insertUser(DBUSer dbuSer);
    @Delete
    void removeUser(DBUSer dbuSer);
    @Update
    void updateUser(DBUSer dbuSer);
}
