package com.example.reza.honarjo.Controller.DBUser;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Model.DBUSer;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
    }
    public LiveData<List<DBUSer>> getAllWords() {
        LiveData<List<DBUSer>> users= null;
        try {
            users = mRepository.getUsers(isLoading);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return users;
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