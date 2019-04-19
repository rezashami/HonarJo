package com.example.reza.honarjo.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Controller.db.ListConverter;

import java.io.Serializable;
import java.util.List;

import static java.lang.String.valueOf;

@Entity(tableName = "users")
@TypeConverters({ListConverter.class})
public class DBUSer  implements Serializable {
    @PrimaryKey
    @NonNull
    private String _id;
    private String name;
    private String family;
    private String phoneNumber;
    private List<Integer> registerDay;
    private List<Integer> expireDay;
    private List<Integer> yellowDay;
    private List<Integer> orangeDay;
    private List<Integer> greenDay;
    private List<Integer> blueDay;
    private List<Integer> brownDay;
    private List<Integer> blackDay;
    private boolean privateCheck;
    private boolean activity;

    public DBUSer() {
    }

    @Ignore
    public DBUSer(User item){
        this._id = item.get_id();
        this.name = item.getName();
        this.family = item.getFamily();
        this.phoneNumber = item.getPhoneNumber();

        this.registerDay = item.getRegisterDay() != null?item.getRegisterDay().getNumeric():null;
        this.expireDay = item.getExpireDay() != null?item.getExpireDay().getNumeric():null;
        this.yellowDay = item.getYellowDay() != null?item.getYellowDay().getNumeric():null;
        this.orangeDay = item.getOrangeDay() != null?item.getOrangeDay().getNumeric():null;
        this.greenDay = item.getGreenDay() != null?item.getGreenDay().getNumeric():null;
        this.blueDay = item.getBlueDay() != null?item.getBlueDay().getNumeric():null;
        this.brownDay = item.getBrownDay() != null?item.getBrownDay().getNumeric():null;
        this.blackDay = item.getBlackDay() != null?item.getBlackDay().getNumeric():null;
        this.privateCheck = item.getIsPrivate();
        this.activity = item.isActivity();

    }
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

    public List<Integer> getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(List<Integer> registerDay) {
        this.registerDay = registerDay;
    }

    public List<Integer> getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(List<Integer> expireDay) {
        this.expireDay = expireDay;
    }

    public List<Integer> getYellowDay() {
        return yellowDay;
    }

    public void setYellowDay(List<Integer> yellowDay) {
        this.yellowDay = yellowDay;
    }

    public List<Integer> getOrangeDay() {
        return orangeDay;
    }

    public void setOrangeDay(List<Integer> orangeDay) {
        this.orangeDay = orangeDay;
    }

    public List<Integer> getGreenDay() {
        return greenDay;
    }

    public void setGreenDay(List<Integer> greenDay) {
        this.greenDay = greenDay;
    }

    public List<Integer> getBlueDay() {
        return blueDay;
    }

    public void setBlueDay(List<Integer> blueDay) {
        this.blueDay = blueDay;
    }

    public List<Integer> getBrownDay() {
        return brownDay;
    }

    public void setBrownDay(List<Integer> brownDay) {
        this.brownDay = brownDay;
    }

    public List<Integer> getBlackDay() {
        return blackDay;
    }

    public void setBlackDay(List<Integer> blackDay) {
        this.blackDay = blackDay;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    @Ignore
    public String toPersianNumber(String text) {
        String[] persianNumbers = new String[]{ "۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹" };
        if (text.length() == 0) {
            return "";
        }
        String out = "";
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(valueOf(c));
                out += persianNumbers[number];
            } else if (c == '٫') {
                out += '،';
            } else {
                out += c;
            }
        }

        return out;
    }

    public boolean isPrivateCheck() {
        return privateCheck;
    }

    public void setPrivateCheck(boolean privateCheck) {
        this.privateCheck = privateCheck;
    }

    @Ignore
    @Override
    public String toString() {
        return "DBUSer{" +
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
                ", privateCheck=" + privateCheck +
                ", activity=" + activity +
                '}';
    }
}
