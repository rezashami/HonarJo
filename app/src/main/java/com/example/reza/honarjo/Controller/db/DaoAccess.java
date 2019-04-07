package com.example.reza.honarjo.Controller.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.reza.honarjo.Model.Insurance;
import com.example.reza.honarjo.Model.User;

import java.util.List;

@Dao
public interface DaoAccess {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();
    @Query("SELECT * FROM insurance")
    LiveData<List<Insurance>> getAllInsurances();

    @Query("SELECT * FROM insurance ORDER BY expireDay DESC")
    LiveData<List<Insurance>> getInsuranceByDate();

    @Insert
    void insertUser(User val);
    @Insert
    void insertInsurance(Insurance prescription);


    @Delete
    void removeUser(User medicine);
    @Update
    void updateUser(User medicine);
    @Update
    void updateInsurance(Insurance alarm);
    @Delete
    void removeInsurance(Insurance alarm);
}
