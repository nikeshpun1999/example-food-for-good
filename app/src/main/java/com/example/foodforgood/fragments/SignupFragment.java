package com.example.foodforgood.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodforgood.API.UserAPI;
import com.example.foodforgood.R;

public class SignupFragment extends Fragment implements View.OnClickListener{
EditText fname,mname,lname,udesc,age,sex,username,nationality,password;
    Button profpick,btnsignup;
    UserAPI uapi;

    public SignupFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_signup, container, false);

        fname=view.findViewById(R.id.signup_firstname);
        mname=view.findViewById(R.id.signup_middlename);
        lname=view.findViewById(R.id.signup_lastname);
        udesc=view.findViewById(R.id.signup_userdesc);
        age=view.findViewById(R.id.signup_age);
        sex=view.findViewById(R.id.signup_sex);
        username=view.findViewById(R.id.signup_Username);
        nationality=view.findViewById(R.id.signup_nationality);
        password=view.findViewById(R.id.signup_Password);
        profpick=view.findViewById(R.id.signup_btn_Profilepic);
        btnsignup=view.findViewById(R.id.signup_btn_signup);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.signup_btn_signup)
        {

        }
    }
}
