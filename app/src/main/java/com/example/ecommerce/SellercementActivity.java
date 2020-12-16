package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecommerce.Model.CementAdopter;
import com.example.ecommerce.Model.Model;
import com.example.ecommerce.Model.MyAdopter;

import java.util.ArrayList;

public class SellercementActivity extends AppCompatActivity {

    private Button add;
    private TextView title;
    private EditText stock, price;
    RecyclerView mRecyclerView;
    CementAdopter cementAdopter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selleractivity);
        getSupportActionBar().setTitle("CEMENT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        stock = (EditText) findViewById(R.id.etsellerstock);
        price = (EditText) findViewById(R.id.etsellerstockprice);
        add = (Button) findViewById(R.id.selleradd);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cementAdopter = new CementAdopter(this, getMyList());
        mRecyclerView.setAdapter(cementAdopter);




    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("ACC CEMENT");
        m.setImg(R.drawable.acccement);
        models.add(m);

        m=new Model();
        m.setTitle("AMBUJA CEMENT");
        m.setImg(R.drawable.ambujacement);
        models.add(m);

        m=new Model();
        m.setTitle("MAHA CEMENT");
        m.setImg(R.drawable.mahacement);
        models.add(m);

        m=new Model();
        m.setTitle("JSW CEMENT");
        m.setImg(R.drawable.jswimg);
        models.add(m);


        m=new Model();
        m.setTitle("MYCEM CEMENT");
        m.setImg(R.drawable.mycemcement);
        models.add(m);

        m=new Model();
        m.setTitle("ULTRATECH CEMENT");
        m.setImg(R.drawable.ultrtechcement);
        models.add(m);







        return models;
    }


}