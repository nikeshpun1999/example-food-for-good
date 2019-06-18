package com.example.foodforgood.API;

import com.example.foodforgood.Model.Usermodel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("/registeruser")
    Call<Void> registeruser(@Body Usermodel usermodel);
}
