package com.example.foodforgood.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.foodforgood.API.UserAPI;
import com.example.foodforgood.Model.Tokenauth;
import com.example.foodforgood.R;
import com.example.foodforgood.Sharedpreference;
import com.example.foodforgood.Views.MainActivity;
import com.example.foodforgood.retrofit.RetrofitBase;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {
    Button signup, login;
    EditText username, password;

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
                Toast.makeText(getActivity(), "entereed", Toast.LENGTH_SHORT).show();
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

    private void Login(){
        if (Validationlogin()){
//            Toast.makeText(getActivity(), "this", Toast.LENGTH_SHORT).show();
            String uname=username.getText().toString().trim();
            String pass=password.getText().toString().trim();

            UserAPI userAPI= RetrofitBase.instance().create(UserAPI.class);
            Call<Tokenauth> calllogin=userAPI.logincheck(uname,pass);

            calllogin.enqueue(new Callback<Tokenauth>() {
                @Override
                public void onResponse(Call<Tokenauth> call, Response<Tokenauth> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getActivity(), "man", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(),response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Tokenauth res=response.body();
                    new Sharedpreference(getActivity()).SessionStart(res.getMessage(),res.getToken());
                  Toast.makeText(getActivity(), "man", Toast.LENGTH_SHORT).show();
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
    }

}
