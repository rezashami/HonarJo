package com.example.reza.honarjo.Controller.DBInsurance;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.reza.honarjo.Controller.db.DaoAccess;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.Insurance;

import java.util.List;
import java.util.concurrent.ExecutionException;

class InsuranceRepository {

    private DaoAccess AppDao;
    InsuranceRepository(Application application) {
        DatabaseManager db = DatabaseManager.getDatabase(application);
        AppDao = db.daoAccess();
    }

    LiveData<List<Insurance>> getInsurances(MutableLiveData<Boolean> isLoading) throws ExecutionException, InterruptedException { return new queryAsyncTask(AppDao,isLoading).execute().get();}
    void update(String id, List<Integer> expireDay){ new updateAsyncTask(AppDao,id,expireDay).execute();}


    private static class updateAsyncTask extends AsyncTask<Void, Void, Void>{
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
            mAsyncTaskDao.updateInsurance(_id,expireDay);
            return null;
        }
    }


    private static class queryAsyncTask extends AsyncTask<Void, Void, LiveData<List<Insurance>>>{
        private DaoAccess mAsyncTaskDao;
        MutableLiveData<Boolean> isLoading;
        queryAsyncTask(DaoAccess alarmDao,MutableLiveData<Boolean> iss) {
            mAsyncTaskDao = alarmDao;
            isLoading =iss;
        }

        @Override
        protected LiveData<List<Insurance>> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAllInsurances();
        }

        @Override
        protected void onPostExecute(LiveData<List<Insurance>> listLiveData) {
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
