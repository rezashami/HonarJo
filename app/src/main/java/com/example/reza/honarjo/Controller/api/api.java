package com.example.reza.honarjo.Controller.api;

import com.example.reza.honarjo.Model.Insurance;
import com.example.reza.honarjo.Model.LoginInformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface api {
//    @GET("/login")
//    Call<List<Insurance>> getInsurances(@Header("token") String token);
//    @POST("/login")
//    Call<LoginResponse> loginJSON(@Body LoginInfo body);
    @Headers("Content-Type: application/json")
    @POST("/login")
    Call<String> login(@Body LoginInformation body);
}
