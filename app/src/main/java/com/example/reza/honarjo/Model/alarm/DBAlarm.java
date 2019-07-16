package com.example.reza.honarjo.Model.alarm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Controller.db.ListConverter;
import com.example.reza.honarjo.Controller.db.UserListConverter;
import com.example.reza.honarjo.Model.MyDate;
import com.example.reza.honarjo.Model.users.ShowingUser;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "alarm")
@TypeConverters({ListConverter.class, UserListConverter.class})
public class DBAlarm implements Serializable {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int id;
    private List<Integer> myDate;
    private List<ShowingUser> users;

    public DBAlarm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ShowingUser> getUsers() {
        return users;
    }

    public void setUsers(List<ShowingUser> users) {
        this.users = users;
    }

    public List<Integer> getMyDate() {
        return myDate;
    }

    public void setMyDate(List<Integer> myDate) {
        this.myDate = myDate;
    }

    @Ignore
    public DBAlarm(List<ShowingUser> users,MyDate date){
        this.users = users;
        this.myDate = date.toList();
    }
}
