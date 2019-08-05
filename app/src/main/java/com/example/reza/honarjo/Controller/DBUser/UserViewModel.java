package com.example.reza.honarjo.Controller.DBUser;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Model.users.DBUSer;
import com.example.reza.honarjo.Model.users.ShowingUser;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
    }

    public LiveData<List<ShowingUser>> getAllWords() {
        LiveData<List<ShowingUser>> users = null;
        try {
            users = mRepository.getUsers();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return users;
    }

    public LiveData<List<ShowingUser>> getUsersByName(String query) {
        LiveData<List<ShowingUser>> myUsers = null;
        try {
            myUsers = mRepository.getUsersByName(query);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return myUsers;
    }

    public void insert(DBUSer dbuSer) {
        mRepository.insert(dbuSer);
    }
}