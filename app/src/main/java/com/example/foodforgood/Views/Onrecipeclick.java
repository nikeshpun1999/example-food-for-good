package com.example.foodforgood.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodforgood.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Onrecipeclick extends AppCompatActivity {

    ImageView recipeimg;
    TextView txtrecipename,txtorigin,txtdirection;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onrecipeclick);
        bundle=getIntent().getExtras();
        Individualrecipe();

    }
    private void Individualrecipe()
    {
        recipeimg=findViewById(R.id.ivrecipeimg);
        txtrecipename=findViewById(R.id.onclick_tvrecipename);
        txtorigin=findViewById(R.id.onclick_tvrecipeorigin);
        txtdirection=findViewById(R.id.onclick_tvdirection);

        if(bundle!=null)
        {
            txtrecipename.setText(bundle.getString("recipeName"));
            txtorigin.setText(bundle.getString("Origin"));
            txtdirection.setText(bundle.getString("direction"));
            String recipimg=bundle.getString("recipeimageName");

            Picasso.with(this).load(recipimg).into(recipeimg);
        }
    }


}
