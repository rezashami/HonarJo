package com.example.reza.honarjo.Controller.DBUser;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.reza.honarjo.Controller.db.DaoAccess;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.DBUSer;

import java.util.List;
import java.util.concurrent.ExecutionException;

class UserRepository {
    private DaoAccess AlarmDao;
    UserRepository(Application application) {
        DatabaseManager db = DatabaseManager.getDatabase(application);
        AlarmDao = db.daoAccess();
    }
    LiveData<List<DBUSer>> getUsers(MutableLiveData<Boolean> isLoading) throws ExecutionException, InterruptedException { return new queryAsyncTask(AlarmDao,isLoading).execute().get();}
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

    private static class queryAsyncTask extends AsyncTask<Void, Void, LiveData<List<DBUSer>>>{
        private DaoAccess mAsyncTaskDao;
        MutableLiveData<Boolean> isLoading;
        queryAsyncTask(DaoAccess alarmDao,MutableLiveData<Boolean> iss) {
            mAsyncTaskDao = alarmDao;
            isLoading =iss;
        }

        @Override
        protected LiveData<List<DBUSer>> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAllUsers();
        }

        @Override
        protected void onPostExecute(LiveData<List<DBUSer>> listLiveData) {
            super.onPostExecute(listLiveData);
            isLoading.setValue(false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isLoading.setValue(true);
        }
    }
}
