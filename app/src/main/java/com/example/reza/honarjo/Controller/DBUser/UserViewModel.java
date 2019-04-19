package com.example.reza.honarjo.Controller.DBUser;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Model.DBUSer;

import java.util.List;

public class UserViewModel  extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<DBUSer>> alarms;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
        alarms = mRepository.getAlarms();
    }
    public LiveData<List<DBUSer>> getAllWords() {
        return alarms;
    }

    public void insert(DBUSer dbuSer) {
        mRepository.insert(dbuSer);
    }
    public void update(DBUSer dbuSer)
    {
        mRepository.update(dbuSer);
    }
    public void remove(DBUSer dbuSer){mRepository.remove(dbuSer);}
}