package com.example.reza.honarjo.Controller.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.reza.honarjo.Model.DBUSer;
import com.example.reza.honarjo.Model.Insurance;
import com.example.reza.honarjo.Model.ShowingUser;

import java.util.List;

@Dao
public interface DaoAccess {
    @Query("SELECT name,family,_id FROM users")
    LiveData<List<ShowingUser>> getAllUsers();

    @Query("SELECT * FROM users WHERE _id=:id")
    DBUSer getOneUser(String id);

    @Query("SELECT name,family,_id FROM users WHERE name LIKE :myQuery OR family LIKE :myQuery")
    LiveData<List<ShowingUser>> getUsersByName(String myQuery);

    @Insert
    void insertUser(DBUSer dbuSer);

    @Delete
    void removeUser(DBUSer dbuSer);

    @Update
    void updateUser(DBUSer dbuSer);

    @Query("SELECT name,family,_id,expireDay FROM users")
    LiveData<List<Insurance>> getAllInsurances();


    @Query("UPDATE users SET expireDay = :end_address WHERE _id = :tid")
    void updateInsurance(String tid, List<Integer> end_address);

}
