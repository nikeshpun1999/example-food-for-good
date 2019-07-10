package com.example.foodforgood.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodforgood.Model.RecipeModel;
import com.example.foodforgood.R;
import com.example.foodforgood.Views.Onrecipeclick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OriginrecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    List<RecipeModel> recipeModelList;
    Context context;

    public static final String BASE_URL="http://10.0.2.2:4500/";

    public OriginrecipeAdapter(List<RecipeModel> recipeModelList, Context context) {
        this.recipeModelList = recipeModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_recycler_view,viewGroup,false);
        return new RecipeAdapter.RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeViewHolder recipeViewHolder, int i) {
        final RecipeModel recipeModel=recipeModelList.get(i);
        recipeViewHolder.tv_heading.setText(recipeModel.getRecipeName());


        Picasso.with(context).load(BASE_URL+recipeModel.getRecipeImgName()).into(recipeViewHolder.img);
        Log.d("log", "onBindViewHolder: "+BASE_URL+"images/"+recipeModel.getRecipeImgName());


        Toast.makeText(context, ""+recipeModelList.size(), Toast.LENGTH_SHORT).show();



        recipeViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context vcontext= v.getContext();
                String send_path=BASE_URL+recipeModel.getRecipeImgName();

                //System.out.println(send_path);

                Intent intent=new Intent(context, Onrecipeclick.class);
                intent.putExtra("recipeName",recipeModel.getRecipeName());
                intent.putExtra("Origin",recipeModel.getOrigin());
                intent.putExtra("recipeimageName",send_path);
                intent.putExtra("direction",recipeModel.getDirection());

                vcontext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return recipeModelList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_heading;
        public ImageView img;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_heading=itemView.findViewById(R.id.sample_heading);
            img=itemView.findViewById(R.id.sample_image);
        }
    }
}
