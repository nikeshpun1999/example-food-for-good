package com.example.foodforgood.API;

import com.example.foodforgood.Model.Tokenauth;
import com.example.foodforgood.Model.Usermodel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPI {


    @POST("users/registeruser")
    Call<String> registeruser(@Body Usermodel usermodel);

    @FormUrlEncoded
    @POST("login")
    Call<Tokenauth> logincheck(@Field("username") String uname, @Field("password") String pass);

}
