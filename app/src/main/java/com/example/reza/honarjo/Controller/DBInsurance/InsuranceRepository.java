package com.example.reza.honarjo.Controller.DBInsurance;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.reza.honarjo.Controller.db.DaoAccess;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.Model.queryResults.ExpireNameFamilyID;

import java.util.Date;
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


    public List<DBAlarm> getDBAlarms() throws ExecutionException, InterruptedException {
        return new GetDBAlarm(AppDao).execute().get();
    }
    public void update(String id, List<Integer> expireDay) {
        new updateAsyncTask(AppDao, id, expireDay).execute();
    }

    public void insert(DBAlarm dbAlarm) {
        new insertAsyncTask(AppDao).execute(dbAlarm);
    }

    public List<ExpireNameFamilyID> get2Column() throws ExecutionException, InterruptedException {
        return new get2ColumnAsyncTask(AppDao).execute().get();
    }

    public List<Date> getDates() throws ExecutionException, InterruptedException {
        return new getDatesAsyncTask(AppDao).execute().get();
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

    public void insertMany(List<DBAlarm> list)
    {
        new insertManyAsyncTask(AppDao).execute(list);
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

    private static class GetDBAlarm extends AsyncTask<Void, Void,List<DBAlarm>> {
        private final DaoAccess mAsyncTaskDao;

        private GetDBAlarm(DaoAccess mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected List<DBAlarm> doInBackground(Void... voids) {
            return mAsyncTaskDao.getInsuranceList();
        }
    }

    private static class getDatesAsyncTask extends AsyncTask<Void, Void, List<Date>> {
        private final DaoAccess myDao;

        getDatesAsyncTask(DaoAccess myDao) {
            this.myDao = myDao;
        }

        @Override
        protected List<Date> doInBackground(Void... voids) {
            return myDao.getDates();
        }
    }

    private static class get2ColumnAsyncTask extends AsyncTask<Void, Void, List<ExpireNameFamilyID>> {
        private final DaoAccess myDao;

        get2ColumnAsyncTask(DaoAccess myDao) {
            this.myDao = myDao;
        }

        @Override
        protected List<ExpireNameFamilyID> doInBackground(Void... voids) {
            return myDao.get2Column();
        }
    }

    private static class insertManyAsyncTask extends AsyncTask<List<DBAlarm>, Void, Void> {

        private DaoAccess mAsyncTaskDao;

        insertManyAsyncTask(DaoAccess dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(List<DBAlarm>... params) {
            for (int i= 0; i<params[0].size();i++)
            {
                mAsyncTaskDao.insertAlarm(params[0].get(i));
            }
            return null;
        }
    }
}
