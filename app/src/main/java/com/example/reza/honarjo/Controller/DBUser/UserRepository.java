package com.example.reza.honarjo.Controller.DBUser;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.reza.honarjo.Controller.db.DaoAccess;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.Model.users.ShowingUser;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {
    private DaoAccess AlarmDao;

    public UserRepository(Application application) {
        DatabaseManager db = DatabaseManager.getDatabase(application);
        AlarmDao = db.daoAccess();
    }

    public LiveData<List<ShowingUser>> getUsers() throws ExecutionException, InterruptedException {
        return new queryAsyncTask(AlarmDao).execute().get();
    }

    public LiveData<List<ShowingUser>> getUsersByName(String name) throws ExecutionException, InterruptedException {
        return new getUsersNames(AlarmDao,name).execute().get();
    }

    public void insert(DBUSer dbuSer) {
        new insertAsyncTask(AlarmDao).execute(dbuSer);
    }
    public void insertMany(List<DBUSer> list)
    {
        new insertManyAsyncTask(AlarmDao).execute(list);
    }

    public void update(DBUSer dbuSer) {
        new updateAsyncTask(AlarmDao).execute(dbuSer);
    }

    public void remove(DBUSer dbuSer) {
        new deleteAsyncTask(AlarmDao).execute(dbuSer);
    }

    private static class insertAsyncTask extends AsyncTask<DBUSer, Void, Void> {

        private DaoAccess mAsyncTaskDao;

        insertAsyncTask(DaoAccess dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DBUSer... params) {
            mAsyncTaskDao.insertUser(params[0]);
            return null;
        }
    }



    private static class insertManyAsyncTask extends AsyncTask<List<DBUSer>, Void, Void> {

        private DaoAccess mAsyncTaskDao;

        insertManyAsyncTask(DaoAccess dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(List<DBUSer>... params) {
            for (int i= 0; i<params[0].size();i++)
            {
                mAsyncTaskDao.insertUser(params[0].get(i));
            }
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<DBUSer, Void, Void> {
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
            mAsyncTaskDao = alarmDao;
        }

        @Override
        protected Void doInBackground(DBUSer... alarms) {
            mAsyncTaskDao.removeUser(alarms[0]);
            return null;
        }
    }

    private static class queryAsyncTask extends AsyncTask<Void, Void, LiveData<List<ShowingUser>>> {
        private DaoAccess mAsyncTaskDao;

        queryAsyncTask(DaoAccess alarmDao) {
            mAsyncTaskDao = alarmDao;
        }

        @Override
        protected LiveData<List<ShowingUser>> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAllUsers();
        }
    }

    private static class getUsersNames extends AsyncTask<Void, Void, LiveData<List<ShowingUser>>>{
        private DaoAccess mAsyncTaskDao;
        private String name;
        getUsersNames(DaoAccess dao, String query) {
            mAsyncTaskDao = dao;
            name = query;
        }

        @Override
        protected LiveData<List<ShowingUser>> doInBackground(Void... voids) {
            return mAsyncTaskDao.getUsersByName(name);
        }
    }
}
