package com.example.reza.honarjo.Controller.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.Model.queryResults.ExpireNameFamilyID;
import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.Model.users.ShowingUser;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoAccess {
    @Insert
    void insertUser(DBUSer dbuSer);

    @Delete
    void removeUser(DBUSer dbuSer);

    @Update
    void updateUser(DBUSer dbuSer);

    @Insert
    void insertAlarm(DBAlarm dbuSer);

    @Delete
    void removeAlarm(DBAlarm dbuSer);

    @Update
    void updateAlarm(DBAlarm dbuSer);

    @Query("SELECT name,family,_id FROM users")
    LiveData<List<ShowingUser>> getAllUsers();

    @Query("SELECT * FROM users WHERE _id=:id")
    DBUSer getOneUser(Integer id);

    @Query("SELECT name,family,_id FROM users WHERE name LIKE :myQuery OR family LIKE :myQuery")
    LiveData<List<ShowingUser>> getUsersByName(String myQuery);

    @Query("SELECT * FROM alarm")
    LiveData<List<DBAlarm>> getAllInsurances();

    @Query("SELECT * FROM alarm")
    List<DBAlarm> getInsuranceList();

    @Query("SELECT * FROM alarm WHERE myDate=:date")
    DBAlarm getOneInsurance(List<Integer> date);

    @Query("UPDATE users SET expireDay = :end_address WHERE _id = :tid")
    void updateInsurance(String tid, List<Integer> end_address);

    @Query("select expireDay from users group by expireDay")
    List<Date> getDates();

    @Query("select expireDay,name,family,_id  from users")
    List<ExpireNameFamilyID> get2Column();
//    @Query("select * from users group by expireDay")
//    void func();

}
