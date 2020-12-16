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

public class CementMyAdopter extends RecyclerView.Adapter<CementMyholder> {

    Context c;
    ArrayList<Cement_Register> models;

    public CementMyAdopter(Context C,ArrayList<Cement_Register> models){
        this.c = c;
        this.models=models;
    }


    @NonNull
    @Override
    public CementMyholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sellerironrod,null);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CementMyholder myholder, int position) {
        myholder.mtitle.setText(models.get(position).getCementName());
        myholder.mstock.setText(models.get(position).getCementStock());
        myholder.mprice.setText(models.get(position).getCementPrice());
        myholder.mimageView.setImageResource(models.get(position).getCementImage());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
