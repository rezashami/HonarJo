package com.example.reza.honarjo.Controller.api;

import com.example.reza.honarjo.Model.alarm.AlarmInformation;
import com.example.reza.honarjo.Model.users.DeleteId;
import com.example.reza.honarjo.Model.LoginInformation;
import com.example.reza.honarjo.Model.users.UpdateUser;
import com.example.reza.honarjo.Model.users.User;

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

    @Headers("Content-Type: application/json")
    @GET("/users")
    Call<List<User>> getAllUsers(@Header("token") String token);

    @Headers("Content-Type: application/json")
    @POST("/users/newuser")
    Call<User> addNewUser(@Header("token") String token, @Body User user);

    @Headers("Content-Type: application/json")
    @POST("/users/update")
    Call<User> updateUser(@Header("token") String token, @Body UpdateUser user);

    @Headers("Content-Type: application/json")
    @POST("/users/delete")
    Call<String> deleteUser(@Header("token") String token, @Body DeleteId user);

    @Headers("Content-Type: application/json")
    @GET("/insurances")
    Call<List<AlarmInformation>> getInsurancesAlarm(@Header("token") String token);

}
