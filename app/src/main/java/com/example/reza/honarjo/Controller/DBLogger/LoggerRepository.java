package com.example.reza.honarjo.Controller.DBLogger;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.reza.honarjo.Controller.db.DaoAccess;
import com.example.reza.honarjo.Controller.db.DatabaseManager;
import com.example.reza.honarjo.Model.logger.DBLogger;

import java.util.List;

class LoggerRepository {
    private DaoAccess ReportDao;
    private LiveData<List<DBLogger>> reports;

    LoggerRepository(Application application) {
        DatabaseManager db = DatabaseManager.getDatabase(application);
        ReportDao = db.daoAccess();
        reports = ReportDao.getAllLogs();
    }

    LiveData<List<DBLogger>> getAllReports() {
        return reports;
    }

    void insert(DBLogger log) {
        new insertAsyncTask(ReportDao).execute(log);
    }

    void remove(DBLogger alarm) {
        new deleteAsyncTask(ReportDao).execute(alarm);
    }

    private static class insertAsyncTask extends AsyncTask<DBLogger, Void, Void> {

        private DaoAccess mAsyncTaskDao;

        insertAsyncTask(DaoAccess dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DBLogger... params) {
            mAsyncTaskDao.insertLog(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<DBLogger, Void, Void> {
        private DaoAccess mAsyncTaskDao;

        deleteAsyncTask(DaoAccess alarmDao) {
            mAsyncTaskDao = alarmDao;
        }

        @Override
        protected Void doInBackground(DBLogger... alarms) {
            mAsyncTaskDao.removeLog(alarms[0]);
            return null;
        }
    }
}
