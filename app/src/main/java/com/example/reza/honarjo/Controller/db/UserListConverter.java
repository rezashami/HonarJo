package com.example.reza.honarjo.Controller.db;

import android.arch.persistence.room.TypeConverter;

import com.example.reza.honarjo.Model.users.ShowingUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class UserListConverter {
    private Gson gson = new Gson();

    @TypeConverter
    public List<ShowingUser> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ShowingUser>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToString(List<ShowingUser> someObjects) {
        return gson.toJson(someObjects);
    }
}
