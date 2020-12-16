package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecommerce.Model.HardwareAdopter;
import com.example.ecommerce.Model.Model;
import com.example.ecommerce.Model.MyAdopter;

import java.util.ArrayList;

public class HardwareActivity extends AppCompatActivity
{
    RecyclerView mRecyclerView;
    HardwareAdopter myAdopter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdopter= new HardwareAdopter(this,getMyList());
        mRecyclerView.setAdapter(myAdopter);
    }
    private ArrayList<Model> getMyList() {
        ArrayList<Model> models= new ArrayList<>();
        Model m=new Model();
        m.setTitle("Wrench");
        m.setImg(R.drawable.wren);
        models.add(m);

        m=new Model();
        m.setTitle("Driller");
        m.setImg(R.drawable.driller);
        models.add(m);

        m=new Model();
        m.setTitle("Taparia Spanners");
        m.setImg(R.drawable.tapariaspanners);
        models.add(m);



        return models;
    }
}