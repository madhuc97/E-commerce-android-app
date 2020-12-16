package com.example.ecommerce.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;

import java.util.ArrayList;

public class PaintAdopter extends RecyclerView.Adapter<PaintHolder>
{

    Context c;
    ArrayList<Model> models;

    public PaintAdopter(Context C,ArrayList<Model> models){
        this.c = c;
        this.models=models;
    }
    @NonNull
    @Override
    public PaintHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_sellercement,null);


        return new PaintHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PaintHolder paintholder, int position) {
        paintholder.mtitle.setText(models.get(position).getTitle());
        paintholder.mstock.setText(models.get(position).getStock());
        paintholder.mprice.setText(models.get(position).getPrice());
        paintholder.mimageView.setImageResource(models.get(position).getImg());

    }

    @Override
    public int getItemCount()
    {
        return models.size();
    }
}
