package com.example.reza.honarjo.Model;

public class User {

    private String _id;
    private String name;
    private String family;
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
