package com.example.foodforgood.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.foodforgood.API.UserAPI;
import com.example.foodforgood.Model.Usermodel;
import com.example.foodforgood.R;
import com.example.foodforgood.retrofit.RetrofitBase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    EditText fname, mname, lname, udesc, tsex, username, tnationality, tpassword;
    TextView profileimgname;
    Button profilepick, btnupdateprofile;
    ImageView profileimg;
    UserAPI uapi;
    String id;
    Uri imageUri;
    Bitmap bitmap;
    private static final int PICK_IMAGE = 1;

    public static final String IMAGE_URL="http://10.0.2.2:4500/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fname = findViewById(R.id.profile_firstname);
        mname = findViewById(R.id.profile_middlename);
        lname = findViewById(R.id.profile_lastname);
        udesc = findViewById(R.id.profile_userdesc);
        profileimgname = findViewById(R.id.profile_picname);
//        tage=findViewById(R.id.profile_age);
        tsex = findViewById(R.id.profile_sex);
        username = findViewById(R.id.profile_Username);
        tnationality = findViewById(R.id.profile_nationality);
        tpassword = findViewById(R.id.profile_Password);

        profilepick = findViewById(R.id.profile_btn_updateimg);
        profilepick.setOnClickListener(this);
        btnupdateprofile = findViewById(R.id.profile_btn_updateprofile);
        btnupdateprofile.setOnClickListener(this);
        profileimg = findViewById(R.id.profile_profileimg);


        SharedPreferences preferences = getSharedPreferences("UserData", 0);
        id = preferences.getString("userid", null);
        Toast.makeText(this, "Userid" + id, Toast.LENGTH_SHORT).show();
        UserAPI userAPI = RetrofitBase.instance().create(UserAPI.class);
        Call<Usermodel> userupdatecall = userAPI.profiledata(id);
        userupdatecall.enqueue(new Callback<Usermodel>() {
            @Override
            public void onResponse(Call<Usermodel> call, Response<Usermodel> response) {
                Usermodel usermodel = response.body();

                fname.setText(usermodel.getFname());
                mname.setText(usermodel.getMname());
                lname.setText(usermodel.getLname());
                udesc.setText(usermodel.getUserDesc());

                tsex.setText(usermodel.getSex());
                username.setText(usermodel.getUsername());
                tnationality.setText(usermodel.getNationality());
                tpassword.setText(usermodel.getPassword());

                Toast.makeText(Profile.this, usermodel.get_id(), Toast.LENGTH_SHORT).show();

                StrictMode();
                try {

                    String imgurl = IMAGE_URL+ usermodel.getProfilePic();
                    URL url = new URL(imgurl);
                    profileimg.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Usermodel> call, Throwable t) {
                Toast.makeText(Profile.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


            }
        });


        Toast.makeText(this, "Yeppi" + id, Toast.LENGTH_SHORT).show();


    }

    private void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_btn_updateimg: {
                Opengallery();
                break;
            }
            case R.id.profile_btn_updateprofile: {
                updateProfile();
                break;
            }
        }
    }

    private void Opengallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileimg.setImageBitmap(bitmap);
                uploadImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        try {

            File file = new File(getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();

            RequestBody rb = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), rb);

            UserAPI userAPI = RetrofitBase.instance().create(UserAPI.class);
            Call<String> imageModelCall = userAPI.uploadImage(body);
            imageModelCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(Profile.this, response.body(), Toast.LENGTH_SHORT).show();
                    profileimgname.setText(response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(Profile.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void updateProfile() {


        String firstname = fname.getText().toString();
        String middlename = mname.getText().toString();
        String lastname = lname.getText().toString();
        String userdesc = udesc.getText().toString();
        String profimgname = profileimgname.getText().toString();
        String sex = tsex.getText().toString();
        String uname = username.getText().toString();
        String nationality = tnationality.getText().toString();
        String password = tpassword.getText().toString();
        String imagename = profileimgname.getText().toString();

        SharedPreferences preferences = getSharedPreferences("UserData", 0);
        String token = preferences.getString("token", null);
        String ids = preferences.getString("userid", "");
        //Log.d("udi",ids);

        UserAPI userAPI = RetrofitBase.instance().create(UserAPI.class);
//        if (imagename == null || imagename.equals("")) {
            Usermodel usermodel=new Usermodel(firstname, middlename, lastname,
                    uname, password, userdesc,profimgname,sex, nationality);
            Call<Void> updateuserprofile = userAPI.updateprofile(usermodel,ids);
            updateuserprofile.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(Profile.this, "not successful", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(Profile.this, "asdasdasdasd", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(Profile.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

//}
