package com.example.foodforgood.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.foodforgood.API.UserAPI;
import com.example.foodforgood.BussinessLogic.LoginBL;
import com.example.foodforgood.Model.Tokenauth;
import com.example.foodforgood.R;
import com.example.foodforgood.Sharedpreference;
import com.example.foodforgood.Views.Dashboard;
import com.example.foodforgood.Views.MainActivity;
import com.example.foodforgood.retrofit.RetrofitBase;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {
    Button signup, login;
    EditText username, password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Boolean isLoggedin=false;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" + //at least 1 digit
                    "(?=.*[a-z])" + //at least 1 lower case letter
                    "(?=.*[A-Z])" +  //at least 1 upper case Letter
                    "(?=\\S+$)" +   //no white spaces
                    ".{8,}" + //at least 8 characters
                    "$");

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        username = view.findViewById(R.id.et_username);
        password = view.findViewById(R.id.et_password);

        signup = view.findViewById(R.id.btn_login_signup);
        login = view.findViewById(R.id.btn_signin);

        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        preferences= getActivity().getSharedPreferences("UserData" , 0);
        editor= preferences.edit();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_signup:
                TabLayout tab = (TabLayout) getActivity().findViewById(R.id.Tablayout);
                tab.getTabAt(1).select();
                break;

            case R.id.btn_signin:
                Login();
                break;
        }
    }

    private boolean Validationlogin() {
        if (username.getText().toString().isEmpty()) {
            username.setError("Please enter your username");
            username.requestFocus();
            return false;
        }

        if (username.getText().toString().isEmpty()) {
            username.setError("Please enter your Password");
            username.requestFocus();
            return false;
        }
        return true;
    }
    public void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy=new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
    private void Login(){

        if (Validationlogin()) {
//            Toast.makeText(getActivity(), "this", Toast.LENGTH_SHORT).show();
            String uname = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            final LoginBL loginBL = new LoginBL(uname, pass);
            StrictMode();

            if (loginBL.checkUser()) {
                Toast.makeText(getActivity(), "Welcome "+LoginBL.uname, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                editor.putString("Fname", LoginBL.fname).commit();
                editor.putString("Lname",LoginBL.lname).commit();
                editor.putString("Mname",LoginBL.mname).commit();
                editor.putString("Username",LoginBL.uname).commit();
                editor.putString("Password",LoginBL.pass).commit();
                editor.putString("Userdesc",LoginBL.userdesc).commit();
                editor.putString("ProfilePic",LoginBL.profilepic).commit();
                editor.putString("Age",LoginBL.age).commit();
                editor.putString("Sex",LoginBL.sex).commit();
                editor.putString("token",LoginBL.token).commit();
                editor.putString("Nationality",LoginBL.nationality).commit();
                editor.putString("id",LoginBL.id).commit();
                isLoggedin=true;
                editor.putBoolean("isLoggedin",isLoggedin).commit();
                startActivity(intent);
                getActivity().finish();
            }
            else{
                Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }

            /*UserAPI userAPI= RetrofitBase.instance().create(UserAPI.class);
            Call<Tokenauth> calllogin=userAPI.logincheck(uname,pass);

            calllogin.enqueue(new Callback<Tokenauth>() {
                @Override
                public void onResponse(Call<Tokenauth> call, Response<Tokenauth> response) {
                    if(!response.isSuccessful()){
//                        Toast.makeText(getActivity(), "man", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(),response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Tokenauth res=response.body();
//                    new Sharedpreference(getActivity()).SessionStart(res.getMessage,res.getToken());
                  Toast.makeText(getActivity(), "man", Toast.LENGTH_SHORT).show();

                    editor.putString("token",res.getToken());
                    editor.putString("userid",res.getId());
                    editor.putString("usernationality",res.getNationality());
                    editor.commit();
//                    Log.d("userid",res.getUser().getUserid());
//                    editor.putString("firstname",res.getUser().getFirstname());
//                    editor.putString("middlename",res.getUser().getMiddlename());
//                    editor.putString("lastname",res.getUser().getLastname());
//                    editor.putString("username",res.getUser().getUname());
//                    editor.putString("password",res.getUser().getPassword());
//                    editor.putString("userdesc",res.getUser().getUserdescription());

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

                @Override
                public void onFailure(Call<Tokenauth> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/
        }
    }
}
