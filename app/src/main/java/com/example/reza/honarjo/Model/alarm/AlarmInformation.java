package com.example.reza.honarjo.Model.alarm;

import com.example.reza.honarjo.Model.MyDate;
import com.example.reza.honarjo.Model.users.ShowingUser;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlarmInformation {
    @SerializedName("_id")
    private MyDate myDate;
    @SerializedName("users")
    private List<ShowingUser> users;

    public MyDate getMyDate() {
        return myDate;
    }

    public void setMyDate(MyDate myDate) {
        this.myDate = myDate;
    }

    public List<ShowingUser> getUserId() {
        return users;
    }

    public void setUserId(List<ShowingUser> userId) {
        this.users = userId;
    }
}
