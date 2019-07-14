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
    private static final String USER_FETCH = "userFetch";
    private static final String INSURANCE_FETCH = "insuranceFetch";

    @SuppressLint("CommitPrefEdits")
    public PreferenceManager(Context ctx) {
        sharedPreferences = ctx.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
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

    public void setUserFetch(boolean isUserFetch) {
        spEditor.putBoolean(USER_FETCH, isUserFetch);
        spEditor.commit();
    }

    public boolean userFetch() {
        return sharedPreferences.getBoolean(USER_FETCH, true);
    }

    public void setInsuranceFetch(boolean isInsuranceFetch) {
        spEditor.putBoolean(INSURANCE_FETCH, isInsuranceFetch);
        spEditor.commit();
    }

    public boolean insuranceFetch() {
        return sharedPreferences.getBoolean(INSURANCE_FETCH, true);
    }
}
