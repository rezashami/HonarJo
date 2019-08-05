package com.example.reza.honarjo.Model.alarm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Controller.db.DateConverter;
import com.example.reza.honarjo.Controller.db.ListConverter;
import com.example.reza.honarjo.Controller.db.UserListConverter;
import com.example.reza.honarjo.Model.users.ShowingUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import saman.zamani.persiandate.PersianDate;

@Entity(tableName = "alarm")
@TypeConverters({ListConverter.class, UserListConverter.class, DateConverter.class})
public class DBAlarm implements Serializable {
    @NonNull
    @PrimaryKey
    private int id;
    private Date myDate;
    private List<ShowingUser> users;

    public DBAlarm() {
        users = new ArrayList<>();
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

    @Ignore
    public DBAlarm(List<ShowingUser> users, Date date) {
        this.users = users;
        this.myDate = date;
    }

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }

    @Ignore
    public void addUser(ShowingUser user) {
        users.add(user);
    }

    @Ignore
    public void removeUser(Integer showingUserID) {
        int userIndex = 0;
        for (int i = 0; i < users.size(); i++) {
            if (showingUserID.equals(users.get(i).get_id())) {
                userIndex = i;
                break;
            }
        }
        users.remove(userIndex);
    }

    @Ignore
    @Override
    public String toString() {

        String da;
        if (myDate == null) da = "ثبت نشده";
        else {
            PersianDate persianDate = new PersianDate(myDate.getTime());
            da = persianDate.toString();
        }
        StringBuilder printUsers = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            printUsers.append(users.toString()).append("\n");
        }

        return "هشدار :" + "\n" +
                "در تاریخ: " + da + "\n"+
                " مشخصات کاربران: " + printUsers.toString();
    }
}
