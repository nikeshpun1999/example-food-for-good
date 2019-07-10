package com.example.foodforgood.Views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodforgood.API.UserAPI;
import com.example.foodforgood.Model.RecipeModel;
import com.example.foodforgood.R;
import com.example.foodforgood.retrofit.RetrofitBase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Craftrecipe extends AppCompatActivity implements View.OnClickListener
{

    EditText et_recipename,et_origin,et_uid,et_raw1,et_raw2,et_raw3,et_raw4,et_raw5,et_raw6,et_raw7,et_raw8,et_raw9,et_direction,et_duration,et_servingcount,et_difficulty,et_recipimgname;
    Button btn_camera,btn_gallery,btn_registerRecipe;
    ImageView craft_recipeimageview;
    Uri imageUri;
    Bitmap bitmap;
    RecipeModel recipeModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craftrecipe);

        et_recipename=findViewById(R.id.craft_recipename);
        craft_recipeimageview=findViewById(R.id.craft_recipeimageview);
        et_origin=findViewById(R.id.craft_origin);
        et_uid=findViewById(R.id.craft_Userid);
        et_raw1=findViewById(R.id.craft_raw1);
        et_raw2=findViewById(R.id.craft_raw2);
        et_raw3=findViewById(R.id.craft_raw3);
        et_raw4=findViewById(R.id.craft_raw4);
        et_raw5=findViewById(R.id.craft_raw5);
        et_raw6=findViewById(R.id.craft_raw6);
        et_raw7=findViewById(R.id.craft_raw7);
        et_raw8=findViewById(R.id.craft_raw8);
        et_raw9=findViewById(R.id.craft_raw9);
        et_direction=findViewById(R.id.craft_direction);
        et_duration=findViewById(R.id.craft_duration);
        et_servingcount=findViewById(R.id.craft_servingcount);
        et_difficulty=findViewById(R.id.craft_difficulty);
        et_recipimgname=findViewById(R.id.craft_recipeImagename);
        btn_camera=findViewById(R.id.btn_opencamera);
        btn_registerRecipe=findViewById(R.id.btn_craftrecipe);
        btn_gallery=findViewById(R.id.btn_recipegallary);
        btn_gallery.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        btn_registerRecipe.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.btn_recipegallary):
            {
                Opengallery();
                break;
            }
            case(R.id.btn_opencamera):
                {
                    opencamera();
                    break;
                }
                case(R.id.btn_craftrecipe):
                {
                    recipecrafting();
                    break;
                }
        }
    }

    private void recipecrafting()
    {
        String recipename=et_recipename.getText().toString();
        String origin=et_origin.getText().toString();
        String uid =et_uid.getText().toString();
        String raw1=et_raw1.getText().toString();
        String raw2=et_raw2.getText().toString();
        String raw3=et_raw3.getText().toString();
        String raw4=et_raw4.getText().toString();
        String raw5=et_raw5.getText().toString();
        String raw6=et_raw6.getText().toString();
        String raw7=et_raw7.getText().toString();
        String raw8=et_raw8.getText().toString();
        String raw9=et_raw9.getText().toString();
        String direction=et_direction.getText().toString();
        String duration=et_duration.getText().toString();
        String servingcount=et_servingcount.getText().toString();
        String difficulty=et_difficulty.getText().toString();
        String recipimgname=et_recipimgname.getText().toString();




    }

    private void opencamera()
    {
        Intent cameraintent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraintent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(cameraintent,2);
        }
    }

    private void Opengallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                craft_recipeimageview.setImageBitmap(bitmap);
                uploadImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 2 && resultCode == RESULT_OK)
        {
            Bundle extras=data.getExtras();
            if(extras==null)
            {
                return;
            }
            else{
                bitmap=(Bitmap) extras.get("data");
                craft_recipeimageview.setImageBitmap(bitmap);
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
                    Toast.makeText(Craftrecipe.this, response.body(), Toast.LENGTH_SHORT).show();
                    et_recipimgname.setText(response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(Craftrecipe.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy=new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
}
