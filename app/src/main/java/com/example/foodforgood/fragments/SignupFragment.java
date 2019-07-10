package com.example.foodforgood.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodforgood.API.UserAPI;
import com.example.foodforgood.BussinessLogic.RegisterBL;
import com.example.foodforgood.Model.Usermodel;
import com.example.foodforgood.R;
    import com.example.foodforgood.retrofit.RetrofitBase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupFragment extends Fragment implements View.OnClickListener{
EditText fname,mname,lname,udesc,tage,tsex,username,tnationality,tpassword;
    Button btnsignup;
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
        tage=view.findViewById(R.id.signup_age);
        tsex=view.findViewById(R.id.signup_sex);
        username=view.findViewById(R.id.signup_Username);
        tnationality=view.findViewById(R.id.signup_nationality);
        tpassword=view.findViewById(R.id.signup_Password);
//        profpick=view.findViewById(R.id.signup_btn_Profilepic);
        btnsignup=view.findViewById(R.id.signup_btn_signup);
        btnsignup.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.signup_btn_signup)
        {
//            Toast.makeText(getActivity(), "entered", Toast.LENGTH_SHORT).show();
            userregistration();

        }
    }

    private void userregistration(){
        String firstname=fname.getText().toString();
        String middlename=mname.getText().toString();
        String lastname=lname.getText().toString();
        String userdescription=udesc.getText().toString();
        Double age=Double.parseDouble(tage.getText().toString());
        String sex=tsex.getText().toString();
        String uname=username.getText().toString();
        String nationality=tnationality.getText().toString();
        String password=tpassword.getText().toString();
        String profilepic="default.jpg";
//        String Profilepic=profpick.getText().toString();


        //Usermodel usermodel=new Usermodel();
        final RegisterBL registerBL=new RegisterBL(firstname,middlename,lastname,uname,password,userdescription,profilepic,"",sex,nationality);
        StrictMode();
        if (registerBL.registerUser()){
            Toast.makeText(getActivity(), "Registered", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
        }
        //UserAPI userAPI= RetrofitBase.instance().create(UserAPI.class);
        /*Call<String> usercall=userAPI.registeruser(usermodel);
        ((Call) usercall).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Registered"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("res",response.body().toString());
                    return;
                }
                String res= (String) response.body();
                Toast.makeText(getActivity(),res, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    public void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy=new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

}
