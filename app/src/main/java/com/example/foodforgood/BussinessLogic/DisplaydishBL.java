package com.example.foodforgood.BussinessLogic;

import com.example.foodforgood.API.RecipeAPI;
import com.example.foodforgood.API.UserAPI;
import com.example.foodforgood.Model.RecipeModel;
import com.example.foodforgood.url.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplaydishBL {
    Retrofit retrofit;
    RecipeAPI recipeAPI;
    boolean isSuccess=false;
    public static List<RecipeModel> recipeModels;

    public void createInstance()
    {
        retrofit=new Retrofit.Builder().baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recipeAPI=retrofit.create(RecipeAPI.class);
    }

    public List<RecipeModel> latestdish(){
        createInstance();
        Call<List<RecipeModel>> recipelist=recipeAPI.getallrecipes();
        try{
            Response<List<RecipeModel>> reciperesponse=recipelist.execute();

            if(reciperesponse.isSuccessful())
            {
                recipeModels=reciperesponse.body();
                return recipeModels;
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return recipeModels;
    }
}
