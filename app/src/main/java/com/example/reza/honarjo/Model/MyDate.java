package com.example.reza.honarjo.Model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MyDate {
    private String year;
    private String month;
    private String daymonth;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDaymonth() {
        return daymonth;
    }

    public void setDaymonth(String daymonth) {
        this.daymonth = daymonth;
    }

    @NonNull
    @Override
    public String toString() {
        return  year + " - " + month + " - " +daymonth ;
    }
    public List<Integer> getNumeric(){
        List<Integer> integers = new ArrayList<>(3);
        if (year == null || month == null || daymonth == null ||
                year.length() == 0 || month.length() == 0 || daymonth.length() == 0 )
            return integers;
        integers.add(Integer.parseInt(this.year));
        integers.add(Integer.parseInt(this.month));
        integers.add(Integer.parseInt(this.daymonth));
        return integers;
    }
}
