package com.example.foodforgood.BussinessLogic;

import com.example.foodforgood.API.RecipeAPI;
import com.example.foodforgood.API.UserAPI;
import com.example.foodforgood.Model.Tokenauth;
import com.example.foodforgood.Model.Usermodel;
import com.example.foodforgood.url.URL;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginBL {
    Retrofit retrofit;
    UserAPI userAPI;

    private String username;
    private String password;

    public static String fname,mname,lname,uname,pass,userdesc,profilepic,age,sex,nationality,token,id;

    boolean isSuccess=false;

    public LoginBL(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void createInstance()
    {
        retrofit=new Retrofit.Builder().baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userAPI=retrofit.create(UserAPI.class);
    }

    public boolean checkUser()
    {
        createInstance();
        //Usermodel usermodel=new Usermodel("","","",username,password,"","","","");
        Call<Tokenauth> userCall=userAPI.logincheck(username,password);
        try{
            Response<Tokenauth> loginResponse=userCall.execute();
            if (loginResponse.isSuccessful() && loginResponse.body().getToken()!=null){
                isSuccess=true;
                id=loginResponse.body().getId();
                fname=loginResponse.body().getFname();
                lname=loginResponse.body().getLname();
                mname=loginResponse.body().getMname();
                age=loginResponse.body().getAge();
                uname=loginResponse.body().getUsername();
                pass=loginResponse.body().getPassword();
                sex=loginResponse.body().getSex();
                nationality=loginResponse.body().getNationality();
                profilepic=loginResponse.body().getProfilepic();
                userdesc=loginResponse.body().getUserdesc();
                token=loginResponse.body().getToken();

            }
            else{
                isSuccess=false;
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
