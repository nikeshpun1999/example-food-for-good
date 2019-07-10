package com.example.foodforgood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.foodforgood.Views.MainActivity;
import com.example.foodforgood.Views.Switch;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences preference;
    Boolean isLoggedin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    preference=getSharedPreferences("UserData", Context.MODE_PRIVATE);
    isLoggedin=preference.getBoolean("isLoggedin",false);
    if(isLoggedin) {
        final Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();

            }

        }, 3000);
    }
    else {
        final Intent intent = new Intent(SplashScreen.this, Switch.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();

            }

        }, 3000);

    }


    }
    }

