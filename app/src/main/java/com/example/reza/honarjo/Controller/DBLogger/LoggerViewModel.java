package com.example.reza.honarjo.Controller.DBLogger;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Model.logger.DBLogger;

import java.util.List;

public class LoggerViewModel extends AndroidViewModel {
    private LoggerRepository mRepository;

    private LiveData<List<DBLogger>> reports;

    public LoggerViewModel(@NonNull Application application) {
        super(application);
        mRepository = new LoggerRepository(application);
        reports = mRepository.getAllReports();
    }

    public LiveData<List<DBLogger>> getAllReports() {
        return reports;
    }

    public void insert(DBLogger dbLogger) {
        mRepository.insert(dbLogger);
    }

    public void remove(DBLogger dbLogger) {
        mRepository.remove(dbLogger);
    }
}
