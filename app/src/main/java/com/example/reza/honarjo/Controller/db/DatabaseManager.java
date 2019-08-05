package com.example.reza.honarjo.Controller.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.Model.logger.DBLogger;
import com.example.reza.honarjo.Model.users.DBUSer;

@Database(entities = {DBUSer.class, DBAlarm.class, DBLogger.class},exportSchema = false, version = 1)
@TypeConverters({ListConverter.class, UserListConverter.class,DateConverter.class})
public abstract class DatabaseManager  extends RoomDatabase {
    public abstract DaoAccess daoAccess();

    private static DatabaseManager INSTANCE;
    private static final String DB_NAME = "applicationUsers.db";

    public static DatabaseManager getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,DatabaseManager.class, DB_NAME).build();
        }
        return INSTANCE;
    }
}
