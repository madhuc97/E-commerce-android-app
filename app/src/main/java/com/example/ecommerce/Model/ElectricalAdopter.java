package com.example.ecommerce.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;

import java.util.ArrayList;

public class ElectricalAdopter extends RecyclerView.Adapter<ElectricalHolder>{
    Context c;
    ArrayList<Model> models;

    public ElectricalAdopter(Context C, ArrayList<Model> models) {
        this.c = c;
        this.models = models;

    }


    @NonNull
    @Override
    public ElectricalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sellerironrod,null);


        return new ElectricalHolder(view);


    }



    @Override
    public void onBindViewHolder(@NonNull ElectricalHolder electricalholder, int position) {
        electricalholder.mtitle.setText(models.get(position).getTitle());
        electricalholder.mstock.setText(models.get(position).getStock());
        electricalholder.mprice.setText(models.get(position).getPrice());
        electricalholder.mimageView.setImageResource(models.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}





