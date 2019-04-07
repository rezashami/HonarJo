package com.example.reza.honarjo.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Controller.db.DateConverter;

import java.util.Date;

@Entity(tableName = "user")
@TypeConverters({DateConverter.class})
public class User {
    @PrimaryKey
    @NonNull
    private String _id;
    private String name;
    private String family;
    private String phoneNumber;
    private Date registerDay;
    private Date expireDay;
    private Date yellowDay;
    private Date orangeDay;
    private Date greenDay;
    private Date blueDay;
    private Date brownDay;
    private Date blackDay;
    private boolean isPrivate;
    private boolean activity;

    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(Date registerDay) {
        this.registerDay = registerDay;
    }

    public Date getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Date expireDay) {
        this.expireDay = expireDay;
    }

    public Date getYellowDay() {
        return yellowDay;
    }

    public void setYellowDay(Date yellowDay) {
        this.yellowDay = yellowDay;
    }

    public Date getOrangeDay() {
        return orangeDay;
    }

    public void setOrangeDay(Date orangeDay) {
        this.orangeDay = orangeDay;
    }

    public Date getGreenDay() {
        return greenDay;
    }

    public void setGreenDay(Date greenDay) {
        this.greenDay = greenDay;
    }

    public Date getBlueDay() {
        return blueDay;
    }

    public void setBlueDay(Date blueDay) {
        this.blueDay = blueDay;
    }

    public Date getBrownDay() {
        return brownDay;
    }

    public void setBrownDay(Date brownDay) {
        this.brownDay = brownDay;
    }

    public Date getBlackDay() {
        return blackDay;
    }

    public void setBlackDay(Date blackDay) {
        this.blackDay = blackDay;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }
}
