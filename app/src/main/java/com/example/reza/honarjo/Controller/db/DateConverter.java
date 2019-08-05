package com.example.reza.honarjo.Controller.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    static Date toDate(Long dateLong) {
        return dateLong == null ? null : new Date(dateLong);
    }

    @TypeConverter
    static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
