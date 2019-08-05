package com.example.reza.honarjo.Model.users;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Controller.db.DateConverter;
import com.example.reza.honarjo.Controller.db.ListConverter;

import java.io.Serializable;
import java.util.Date;

import static com.example.reza.honarjo.Controller.timeConverter.TimeConverter.getPersianDashedTime;

@Entity(tableName = "users")
@TypeConverters({ListConverter.class, DateConverter.class})
public class DBUSer implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer _id;
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
    private boolean privateCheck;
    private Integer code;

    public DBUSer() {
    }

    @Ignore
    public DBUSer(String name, String family, String phoneNumber, Date registerDay, Date expireDay, Date yellowDay, Date orangeDay, Date greenDay, Date blueDay, Date brownDay, Date blackDay, boolean privateCheck,Integer code) {
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
        this.privateCheck = privateCheck;
        this.code = code;
    }

    @NonNull
    public Integer get_id() {
        return _id;
    }

    public void set_id(@NonNull Integer _id) {
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


    public boolean isPrivateCheck() {
        return privateCheck;
    }

    public void setPrivateCheck(boolean privateCheck) {
        this.privateCheck = privateCheck;
    }

    @NonNull
    @Ignore
    @Override
    public String toString() {
        String _registerDay = getPersianDashedTime(registerDay);
        String _expireDay = getPersianDashedTime(expireDay);
        String _yellowDay = getPersianDashedTime(yellowDay);
        String _orangeDay = getPersianDashedTime(orangeDay);
        String _greenDay =getPersianDashedTime(greenDay);
        String _blueDay = getPersianDashedTime(blueDay);
        String _brownDay = getPersianDashedTime(brownDay);
        String _blackDay = getPersianDashedTime(blackDay);
        String prv = privateCheck ? "بله": "خیر";
        return "کاربر: " +
                " نام: " + name + '\'' +
                " نام خانوادگی: " + family + '\'' +
                " شماره تماس: " + phoneNumber + '\'' +
                " تاریخ عضویت: " + _registerDay +
                " تاریخ انقضای بیمه: " + _expireDay +
                " تاریخ اخذ کمربند زرد: " + _yellowDay+
                " تاریخ اخذ کمربند نارنجی: " + _orangeDay +
                " تاریخ اخذ کمربند سبز: " + _greenDay +
                " تاریخ اخذ کمربند آبی: " + _blueDay +
                " تاریخ اخذ کمربند قهوه‌ای: " + _brownDay +
                " تاریخ اخذ کمربند مشکی: " + _blackDay +
                " ثبت‌نام در کلاس خصوصی" + prv ;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
