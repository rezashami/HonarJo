package com.example.reza.honarjo.Model.users;

import android.support.annotation.NonNull;

import com.example.reza.honarjo.Model.MyDate;
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

    public User(DBUSer dbuSer) {
        this.name = dbuSer.getName();
        this.family = dbuSer.getFamily();
        this.phoneNumber = dbuSer.getPhoneNumber();
        this.registerDay = dbuSer.getMyDate(dbuSer.getRegisterDay());
        this.expireDay = dbuSer.getMyDate(dbuSer.getExpireDay());
        this.yellowDay = dbuSer.getMyDate(dbuSer.getYellowDay());
        this.orangeDay = dbuSer.getMyDate(dbuSer.getOrangeDay());
        this.greenDay = dbuSer.getMyDate(dbuSer.getGreenDay());
        this.blueDay = dbuSer.getMyDate(dbuSer.getBlueDay());
        this.brownDay = dbuSer.getMyDate(dbuSer.getBrownDay());
        this.blackDay = dbuSer.getMyDate(dbuSer.getBlackDay());
        this.isPrivate = dbuSer.isPrivateCheck();
        this.activity = dbuSer.isActivity();
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

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registerDay=" + registerDay +
                ", expireDay=" + expireDay +
                ", yellowDay=" + yellowDay +
                ", orangeDay=" + orangeDay +
                ", greenDay=" + greenDay +
                ", blueDay=" + blueDay +
                ", brownDay=" + brownDay +
                ", blackDay=" + blackDay +
                ", isPrivate=" + isPrivate +
                ", activity=" + activity +
                '}';
    }
}
