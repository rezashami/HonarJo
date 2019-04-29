package com.example.reza.honarjo.Model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class User {

    private String _id;
    private String name;
    private String family;
    @SerializedName("mobileNumber")
    private String phoneNumber;
    private MyDate registerDay;
    private MyDate expireDay;
    private MyDate yellowDay;
    private MyDate orangeDay;
    private MyDate greenDay;
    private MyDate blueDay;
    private MyDate brownDay;
    private MyDate blackDay;
    private boolean isPrivate;
    private boolean activity;

    public User(String name, String family, String phoneNumber, MyDate registerDay, MyDate expireDay, MyDate yellowDay, MyDate orangeDay, MyDate greenDay, MyDate blueDay, MyDate brownDay, MyDate blackDay, boolean isPrivate) {
        this.name = name;
        this.family = family;
        this.phoneNumber = phoneNumber;
        this.registerDay = registerDay;
        this.expireDay = expireDay;
        this.yellowDay = yellowDay;
        this.orangeDay = orangeDay;
        this.greenDay = greenDay;
        this.blueDay = blueDay;
        this.brownDay = brownDay;
        this.blackDay = blackDay;
        this.isPrivate = isPrivate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
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

    public MyDate getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(MyDate registerDay) {
        this.registerDay = registerDay;
    }

    public MyDate getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(MyDate expireDay) {
        this.expireDay = expireDay;
    }

    public MyDate getYellowDay() {
        return yellowDay;
    }

    public void setYellowDay(MyDate yellowDay) {
        this.yellowDay = yellowDay;
    }

    public MyDate getOrangeDay() {
        return orangeDay;
    }

    public void setOrangeDay(MyDate orangeDay) {
        this.orangeDay = orangeDay;
    }

    public MyDate getGreenDay() {
        return greenDay;
    }

    public void setGreenDay(MyDate greenDay) {
        this.greenDay = greenDay;
    }

    public MyDate getBlueDay() {
        return blueDay;
    }

    public void setBlueDay(MyDate blueDay) {
        this.blueDay = blueDay;
    }

    public MyDate getBrownDay() {
        return brownDay;
    }

    public void setBrownDay(MyDate brownDay) {
        this.brownDay = brownDay;
    }

    public MyDate getBlackDay() {
        return blackDay;
    }

    public void setBlackDay(MyDate blackDay) {
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
