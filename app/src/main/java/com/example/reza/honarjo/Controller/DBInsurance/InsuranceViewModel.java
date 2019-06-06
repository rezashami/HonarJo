package com.example.reza.honarjo.Controller.DBInsurance;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.Model.insuranses.Insurance;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class InsuranceViewModel extends AndroidViewModel {
    private InsuranceRepository mRepository;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public InsuranceViewModel(@NonNull Application application) {
        super(application);
        mRepository = new InsuranceRepository(application);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<List<DBAlarm>> getAllInsurances() {
        LiveData<List<DBAlarm>> alarms= null;
        try {
            alarms = mRepository.getInsurances(isLoading);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return alarms;
    }

    public void update(String id, List<Integer> expireDay)
    {
        mRepository.update(id,expireDay);
    }
}
