package com.example.foodforgood.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.widget.Toast;

import com.example.foodforgood.API.RecipeAPI;
import com.example.foodforgood.Adapters.RecipeAdapter;
import com.example.foodforgood.BussinessLogic.DisplaydishBL;
import com.example.foodforgood.Model.RecipeModel;
import com.example.foodforgood.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Latestdish extends AppCompatActivity {

    RecyclerView recyclerView;
    List<RecipeModel> recipeModelList;
    Retrofit retrofit;
    RecipeAPI recipeAPI;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latestdish);

        recyclerView=findViewById(R.id.recyclerView);

        sharedPreferences=this.getSharedPreferences("App", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        getallrecipes();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }
    public void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy=new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
    public void getallrecipes()
    {
        DisplaydishBL displaydishBL=new DisplaydishBL();
        StrictMode();

        if(displaydishBL.latestdish()!=null)
        {
            List<RecipeModel> recipeModelList=displaydishBL.latestdish();
            recyclerView.setAdapter(new RecipeAdapter(recipeModelList,getApplicationContext()));
        }
        else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

//        Call<List<RecipeModel>> recipelistcall=recipeAPI.getallrecipes();
//        recipelistcall.enqueue(new Callback<List<RecipeModel>>() {
//            @Override
//            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
//                List<RecipeModel> recipeModelList=response.body();
//                recyclerView.setAdapter(new RecipeAdapter(recipeModelList,getApplicationContext()));
//            }
//
//            @Override
//            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
//                Toast.makeText(Latestdish.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
}
