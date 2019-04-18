package com.example.reza.honarjo.Controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class appClient {
    private static final String BASE_URL = "https://honar-jo.herokuapp.com/";
    private static Retrofit retrofit;
    public static Retrofit getInstance()
    {
        if (retrofit ==null)
        {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
}
