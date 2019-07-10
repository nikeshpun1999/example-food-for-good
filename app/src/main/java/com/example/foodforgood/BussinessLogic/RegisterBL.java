package com.example.foodforgood.BussinessLogic;

import com.example.foodforgood.API.UserAPI;
import com.example.foodforgood.Model.Tokenauth;
import com.example.foodforgood.Model.Usermodel;
import com.example.foodforgood.url.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterBL {
    Retrofit retrofit;
    UserAPI userAPI;

    public String fname,mname,lname,uname,pass,userdesc,profilepic,age,sex,nationality;

    boolean isSuccess=false;

    public RegisterBL(String fname, String mname, String lname, String uname, String pass, String userdesc, String profilepic, String age, String sex, String nationality) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.uname = uname;
        this.pass = pass;
        this.userdesc = userdesc;
        this.profilepic = profilepic;
        this.age = age;
        this.sex = sex;
        this.nationality = nationality;
    }

    public void createInstance()
    {
        retrofit=new Retrofit.Builder().baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userAPI=retrofit.create(UserAPI.class);
    }

    public boolean registerUser()
    {
        createInstance();
        Usermodel usermodel=new Usermodel(fname,mname,lname,uname,pass,userdesc,profilepic,sex,nationality);
        Call<String> userCall=userAPI.registeruser(usermodel);
        try{
            Response<String> stringResponse=userCall.execute();
            if (stringResponse.isSuccessful()){
                isSuccess=true;
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
