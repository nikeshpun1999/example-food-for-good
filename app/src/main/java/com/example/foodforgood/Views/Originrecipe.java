package com.example.foodforgood.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.foodforgood.API.RecipeAPI;
import com.example.foodforgood.Model.RecipeModel;
import com.example.foodforgood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Originrecipe extends AppCompatActivity {

    RecyclerView recyclerView;
    List<RecipeModel> recipeModelList;
    Retrofit retrofit;
    RecipeAPI recipeAPI;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_originrecipe);

        recyclerView=findViewById(R.id.recyclerView);

        sharedPreferences=this.getSharedPreferences("App", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        getoriginrecipes();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }

    public void getoriginrecipes()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4500/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeAPI=retrofit.create(RecipeAPI.class);

        Call<List<RecipeModel>> recipelistcall=recipeAPI.getrecipebyorigin();

    }
}
