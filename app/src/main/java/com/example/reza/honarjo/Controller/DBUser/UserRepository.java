package com.example.reza.honarjo.Controller.DBUser;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.reza.honarjo.Controller.db.DaoAccess;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.DBUSer;

import java.util.List;

public class UserRepository {
    private DaoAccess AlarmDao;
    private LiveData<List<DBUSer>> alarms;


    UserRepository(Application application) {
        DatabaseManager db = DatabaseManager.getDatabase(application);
        AlarmDao = db.daoAccess();
        alarms = AlarmDao.getAllUsers();
    }

    LiveData<List<DBUSer>> getAlarms() {
        return alarms;
    }
    void insert(DBUSer dbuSer) { new insertAsyncTask(AlarmDao).execute(dbuSer); }
    void update(DBUSer dbuSer){ new updateAsyncTask(AlarmDao).execute(dbuSer);}
    void remove(DBUSer dbuSer){new deleteAsyncTask(AlarmDao).execute(dbuSer);}

    private static class insertAsyncTask extends AsyncTask<DBUSer, Void, Void> {

        private DaoAccess mAsyncTaskDao;

        insertAsyncTask(DaoAccess dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DBUSer... params) {
            Log.e("PRintttt",params[0].toString());
            mAsyncTaskDao.insertUser(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<DBUSer, Void, Void>{
        private DaoAccess mAsyncTaskDao;
        updateAsyncTask(DaoAccess alarmDao) {
            mAsyncTaskDao = alarmDao;
        }

        @Override
        protected Void doInBackground(DBUSer... alarms) {
            mAsyncTaskDao.updateUser(alarms[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<DBUSer, Void, Void> {
        private DaoAccess mAsyncTaskDao;
        deleteAsyncTask(DaoAccess alarmDao) {
            mAsyncTaskDao =alarmDao;
        }

        @Override
        protected Void doInBackground(DBUSer... alarms) {
            mAsyncTaskDao.removeUser(alarms[0]);
            return null;
        }
    }
}
