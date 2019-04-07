package com.example.reza.honarjo.Controller.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.reza.honarjo.Model.Insurance;
import com.example.reza.honarjo.Model.User;

@Database(entities = {User.class,Insurance.class},exportSchema = false, version = 1)
@TypeConverters({DateConverter.class})
public abstract class DBHelper extends RoomDatabase {
    public abstract DaoAccess daoAccess();

    private static DBHelper INSTANCE;
    private static final String DB_NAME = "honarjo.db";

    public static DBHelper getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,DBHelper.class, DB_NAME).build();
        }
        return INSTANCE;

    }
}
