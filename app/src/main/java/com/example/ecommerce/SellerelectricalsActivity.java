package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecommerce.Model.ElectricalAdopter;
import com.example.ecommerce.Model.IronAdopter;
import com.example.ecommerce.Model.Model;

import java.util.ArrayList;

public class SellerelectricalsActivity extends AppCompatActivity {
    private Button add;
    private TextView title;
    private EditText stock, price;
    RecyclerView mRecyclerView;
    ElectricalAdopter electricalAdopter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerelectricals);
        getSupportActionBar().setTitle("ELECTRICAL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stock = (EditText) findViewById(R.id.etsellerironstock);
        price = (EditText) findViewById(R.id.etsellerironprice);
        add = (Button) findViewById(R.id.ironaddtocart);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        electricalAdopter = new ElectricalAdopter(this, getMyList());
        mRecyclerView.setAdapter(electricalAdopter);



    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("PLUG");
        m.setImg(R.drawable.plugi);
        models.add(m);

        m=new Model();
        m.setTitle("MCV");
        m.setImg(R.drawable.mcv);
        models.add(m);

        m=new Model();
        m.setTitle("SWITCH");
        m.setImg(R.drawable.swi);
        models.add(m);


        return models;

    }
}