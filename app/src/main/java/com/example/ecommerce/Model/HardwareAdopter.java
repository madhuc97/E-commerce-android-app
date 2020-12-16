package com.example.ecommerce.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;

import java.util.ArrayList;

public class HardwareAdopter extends RecyclerView.Adapter<HardwareHolder>{
    Context c;
    ArrayList<Model> models;

    public HardwareAdopter(Context C,ArrayList<Model> models){
        this.c = c;
        this.models=models;
    }



    @NonNull
    @Override
    public HardwareHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sellerironrod,null);


        return new HardwareHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull HardwareHolder hardwareHolder, int position) {
        hardwareHolder.mtitle.setText(models.get(position).getTitle());
        hardwareHolder.mstock.setText(models.get(position).getStock());
        hardwareHolder.mprice.setText(models.get(position).getPrice());
        hardwareHolder.mimageView.setImageResource(models.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
