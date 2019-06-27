package com.example.foodforgood.API;

import com.example.foodforgood.Model.Usermodel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPI {


    @POST("users/registeruser")
    Call<String> registeruser(@Body Usermodel usermodel);
}
