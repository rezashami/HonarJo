package com.example.reza.honarjo.Model.users;

public class ReadingUser {
    private String name;
    private String family;
    private String phoneNumber;
    private DateModel registerDay;
    private DateModel expireDay;
    private DateModel yellowDay;
    private DateModel orangeDay;
    private DateModel greenDay;
    private DateModel blueDay;
    private DateModel brownDay;
    private DateModel blackDay;
    private boolean privateCheck;
    private Integer code;

    public ReadingUser() {
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

    public DateModel getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(DateModel registerDay) {
        this.registerDay = registerDay;
    }

    public DateModel getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(DateModel expireDay) {
        this.expireDay = expireDay;
    }

    public DateModel getYellowDay() {
        return yellowDay;
    }

    public void setYellowDay(DateModel yellowDay) {
        this.yellowDay = yellowDay;
    }

    public DateModel getOrangeDay() {
        return orangeDay;
    }

    public void setOrangeDay(DateModel orangeDay) {
        this.orangeDay = orangeDay;
    }

    public DateModel getGreenDay() {
        return greenDay;
    }

    public void setGreenDay(DateModel greenDay) {
        this.greenDay = greenDay;
    }

    public DateModel getBlueDay() {
        return blueDay;
    }

    public void setBlueDay(DateModel blueDay) {
        this.blueDay = blueDay;
    }

    public DateModel getBrownDay() {
        return brownDay;
    }

    public void setBrownDay(DateModel brownDay) {
        this.brownDay = brownDay;
    }

    public DateModel getBlackDay() {
        return blackDay;
    }

    public void setBlackDay(DateModel blackDay) {
        this.blackDay = blackDay;
    }

    public boolean isPrivateCheck() {
        return privateCheck;
    }

    public void setPrivateCheck(boolean privateCheck) {
        this.privateCheck = privateCheck;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
