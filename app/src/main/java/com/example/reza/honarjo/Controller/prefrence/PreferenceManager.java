package com.example.reza.honarjo.Controller.prefrence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;
    private static final String TOKEN = "tokenKey";
    private static final String FIRST_LAUNCH = "firstLaunch";
    private static final String PREFERENCE = "MyPrefs";

    @SuppressLint("CommitPrefEdits")
    public PreferenceManager(Context ctx) {
        sharedPreferences = ctx.getSharedPreferences(PREFERENCE,  Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }

    public void addToken(String token) {
        spEditor.putString(TOKEN, token);
        spEditor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, null);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        spEditor.putBoolean(FIRST_LAUNCH, isFirstTime);
        spEditor.commit();
    }

    public boolean FirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }
}
