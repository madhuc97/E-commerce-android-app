package com.example.ecommerce.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Cement_Register;
import com.example.ecommerce.R;

import java.util.ArrayList;

public class CementAdopter extends RecyclerView.Adapter<CementHolder> {


    Context c;
    ArrayList<Model> models;

    public CementAdopter(Context C, ArrayList<Model> models){
        this.c = c;
        this.models=models;
    }

    @NonNull
    @Override
    public CementHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_sellercement,null);


        return new CementHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CementHolder cementHolder, int position) {

        cementHolder.mtitle.setText(models.get(position).getTitle());
        cementHolder.mstock.setText(models.get(position).getStock());
        cementHolder.mprice.setText(models.get(position).getPrice());
        cementHolder.mimageView.setImageResource(models.get(position).getImg());

    }




    @Override
    public int getItemCount()
    {
        return models.size();
    }
}
