package com.example.reza.honarjo.Controller.DBInsurance;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.reza.honarjo.Controller.db.DaoAccess;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.alarm.DBAlarm;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class InsuranceRepository {

    private DaoAccess AppDao;

    public InsuranceRepository(Application application) {
        DatabaseManager db = DatabaseManager.getDatabase(application);
        AppDao = db.daoAccess();
    }

    public LiveData<List<DBAlarm>> getInsurances(MutableLiveData<Boolean> isLoading) throws ExecutionException, InterruptedException {
        return new queryAsyncTask(AppDao, isLoading).execute().get();
    }

    public void update(String id, List<Integer> expireDay) {
        new updateAsyncTask(AppDao, id, expireDay).execute();
    }

    public void insert(DBAlarm dbAlarm) {
        new insertAsyncTask(AppDao).execute(dbAlarm);
    }

    private static class updateAsyncTask extends AsyncTask<Void, Void, Void> {
        private DaoAccess mAsyncTaskDao;
        private String _id;
        private List<Integer> expireDay;

        updateAsyncTask(DaoAccess alarmDao, String id, List<Integer> expireDay) {
            mAsyncTaskDao = alarmDao;
            _id = id;
            this.expireDay = expireDay;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.updateInsurance(_id, expireDay);
            return null;
        }
    }

    private static class queryAsyncTask extends AsyncTask<Void, Void, LiveData<List<DBAlarm>>> {
        private DaoAccess mAsyncTaskDao;
        MutableLiveData<Boolean> isLoading;

        queryAsyncTask(DaoAccess alarmDao, MutableLiveData<Boolean> iss) {
            mAsyncTaskDao = alarmDao;
            isLoading = iss;
        }

        @Override
        protected LiveData<List<DBAlarm>> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAllInsurances();
        }

        @Override
        protected void onPostExecute(LiveData<List<DBAlarm>> listLiveData) {
            super.onPostExecute(listLiveData);
            isLoading.setValue(false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isLoading.setValue(true);
        }
    }

    private static class insertAsyncTask extends AsyncTask<DBAlarm, Void, Void> {

        private DaoAccess mAsyncTaskDao;

        insertAsyncTask(DaoAccess dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DBAlarm... params) {
            mAsyncTaskDao.insertAlarm(params[0]);
            return null;
        }
    }
}
