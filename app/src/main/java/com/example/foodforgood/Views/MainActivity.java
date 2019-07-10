package com.example.foodforgood.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.design.theme.MaterialComponentsViewInflater;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.foodforgood.R;
import com.example.foodforgood.fragments.LoginFragment;
import com.example.foodforgood.fragments.SignupFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener



{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    LinearLayout hotdishes,latestdishes,origin,profile,craft,wishlist;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    boolean isLoggedIn;

    SensorManager sensorManager;
    private float dataAceelo;
    private float dataAceelocurrent;
    private float dataAceelolast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shake();
        putdown();

        hotdishes=findViewById(R.id.main_hotdish_cv);
        latestdishes=findViewById(R.id.main_fresh_cv);
        origin=findViewById(R.id.main_origin_cv);
        profile=findViewById(R.id.main_profile_cv);
        craft=findViewById(R.id.main_craft_cv);
        wishlist=findViewById(R.id.main_wishlist_cv);

        hotdishes.setOnClickListener(this);
        latestdishes.setOnClickListener(this);
        origin.setOnClickListener(this);
        profile.setOnClickListener(this);
        craft.setOnClickListener(this);
        wishlist.setOnClickListener(this);

        drawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.sideNav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if (id == R.id.nav_db) {
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(MainActivity.this,MainActivity.class));
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();

        }
                else if(id==R.id.nav_logout)
                {
                    Intent intent = new Intent(MainActivity.this, Switch.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    editor = preferences.edit();
                    isLoggedIn = false;
                    editor.putBoolean("isLoggedin", isLoggedIn).commit();
                    startActivity(intent);
                    //do not start activity if you want to exit the app.
                    finish();

                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
        case(R.id.main_hotdish_cv):
        {
//            Intent intent=new Intent(MainActivity.this,Profile.class);
//            startActivity(intent);
            break;
        }
            case(R.id.main_profile_cv):
            {
                Intent intent1=new Intent(MainActivity.this,Profile.class);
                startActivity(intent1);
                break;

            }
            case(R.id.main_fresh_cv):
            {
                Intent intent2=new Intent(MainActivity.this,Latestdish.class);
                startActivity(intent2);
                break;

            }
            case(R.id.main_craft_cv):
            {
                Intent intent=new Intent(MainActivity.this,Craftrecipe.class);
                startActivity(intent);
                break;
            }
    }}

    private void shake(){
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener accellistener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values=event.values;
                float xaxis=values[0];
                float yaxis=values[1];
                float zaxis=values[2];
                dataAceelolast=dataAceelocurrent;
                dataAceelocurrent=(float)Math.sqrt((double) (xaxis*xaxis+yaxis*yaxis+zaxis*zaxis));
                float delta=dataAceelocurrent-dataAceelolast;
                dataAceelo=dataAceelo*0.9f+delta;
                if(dataAceelo>10) {
                    Intent intent=new Intent(MainActivity.this,Craftrecipe.class);
                    startActivity(intent);
                    finish();

                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        if (sensor == null) {
            Toast.makeText(this, "No Accelometer sensor detected", Toast.LENGTH_SHORT).show();
        }
        else {
            sensorManager.registerListener(accellistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void putdown(){
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener accellistener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {

                if(event.values[0]==0) {
                    Intent intent = new Intent(MainActivity.this, Switch.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    editor = preferences.edit();
                    isLoggedIn = false;
                    editor.putBoolean("isLoggedin", isLoggedIn).commit();
                    startActivity(intent);
                    //do not start activity if you want to exit the app.
                    finish();
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        if (sensor == null) {
            Toast.makeText(this, "No Proximity sensor detected", Toast.LENGTH_SHORT).show();
        }
        else {
            sensorManager.registerListener(accellistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


}



