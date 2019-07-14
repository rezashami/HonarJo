package com.example.reza.honarjo.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyDate {
    private Integer year;
    private Integer month;
    private Integer day;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @NonNull
    @Override
    public String toString() {
        String m = this.month < 10 ? "0" + this.month : String.valueOf(this.month);
        String d = this.day < 10 ? "0" + this.day : String.valueOf(this.day);
        return year +
                "-" + m +
                "-" + d;
    }

    public List<Integer> toList() {
        List<Integer> res = new ArrayList<>(3);
        if (year == null || month == null || day == null) {
            res.add(0);
            res.add(0);
            res.add(0);
            return res;
        }
        Log.e("toList",this.day.toString()+" - "+this.month.toString()+" - "+this.year.toString());
        res.add(year);
        res.add(month);
        res.add(day);
        return res;
    }

    public MyDate() {

    }

    public MyDate(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
