package com.example.foodforgood.API;

import com.example.foodforgood.Model.RecipeModel;
import com.example.foodforgood.Model.Usermodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RecipeAPI {

    @GET("recipes/alldishes")
    Call<List<RecipeModel>> getallrecipes();

    @GET("recipes/recipebyorigin")
    Call<List<RecipeModel>> getrecipebyorigin();

    @POST("recipes/craftrecipe")
    Call<String> registerrecipe(@Body RecipeModel recipeModel);
}
