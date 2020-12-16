package com.example.ecommerce.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;

import java.util.ArrayList;

public class IronAdopter extends RecyclerView.Adapter<IronHolder>
{
    Context c;
    ArrayList<Model> models;

    public IronAdopter(Context C,ArrayList<Model> models){
        this.c = c;
        this.models=models;
    }


    @NonNull
    @Override
    public IronHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sellerironrod,null);


        return new IronHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull IronHolder ironholder, int position) {
        ironholder.mtitle.setText(models.get(position).getTitle());
        ironholder.mstock.setText(models.get(position).getStock());
        ironholder.mprice.setText(models.get(position).getPrice());
        ironholder.mimageView.setImageResource(models.get(position).getImg());
    }







    @Override
    public int getItemCount() {
        return models.size();
    }
}
