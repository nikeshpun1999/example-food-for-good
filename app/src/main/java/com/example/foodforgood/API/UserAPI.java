package com.example.foodforgood.API;

import com.example.foodforgood.Model.Tokenauth;
import com.example.foodforgood.Model.Usermodel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserAPI {


    @POST("users/registeruser")
    Call<String> registeruser(@Body Usermodel usermodel);

    @FormUrlEncoded
    @POST("users/login")
    Call<Tokenauth> logincheck(@Field("username") String uname, @Field("password") String pass);

    //    @Multipart
    @GET("users/getuserdata/{id}")
    Call<Usermodel> profiledata(@Path("id") String id);

    @Multipart
    @POST("users/uploadimg")
    Call<String> uploadImage(@Part MultipartBody.Part body);


    @POST("users/updateprofile/Android/{id}")
    Call<Void> updateprofile(@Body Usermodel usermodel,@Path("id") String id);

}
