package com.example.reza.honarjo.Controller.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class appClient {
    private static final String BASE_URL = "http://78.38.32.50:3000";
    private static Retrofit retrofit;
    public static Retrofit getInstance()
    {
        if (retrofit ==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
