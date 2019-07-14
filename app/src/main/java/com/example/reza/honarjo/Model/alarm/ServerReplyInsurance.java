package com.example.reza.honarjo.Model.alarm;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerReplyInsurance {
    @SerializedName("past")
    List<AlarmInformation> pass;
    @SerializedName("notpast")
    List<AlarmInformation> notPass;

    public List<AlarmInformation> getPass() {
        return pass;
    }

    public void setPass(List<AlarmInformation> pass) {
        this.pass = pass;
    }

    public List<AlarmInformation> getNotPass() {
        return notPass;
    }

    public void setNotPass(List<AlarmInformation> notPass) {
        this.notPass = notPass;
    }
}
