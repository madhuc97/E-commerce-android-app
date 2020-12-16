package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Model.IronAdopter;
import com.example.ecommerce.Model.Model;
import com.example.ecommerce.Model.MyAdopter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerIronrodActivity extends AppCompatActivity {
    private Button add;
    private TextView title;
    private EditText stock, price;
    RecyclerView mRecyclerView;
    IronAdopter ironAdopter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ironrod);
        getSupportActionBar().setTitle("IRON");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stock = (EditText) findViewById(R.id.etsellerironstock);
        price = (EditText) findViewById(R.id.etsellerironprice);
        add = (Button) findViewById(R.id.ironaddtocart);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ironAdopter = new IronAdopter(this, getMyList());
        mRecyclerView.setAdapter(ironAdopter);




    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("TATA IRONROD");
        m.setImg(R.drawable.tataironrods);
        models.add(m);

        m=new Model();
        m.setTitle("SRMBTMT IRONROD");
        m.setImg(R.drawable.srmbtmt);
        models.add(m);

        m=new Model();
        m.setTitle("ESSAR IRONROD");
        m.setImg(R.drawable.essar);
        models.add(m);

        m=new Model();
        m.setTitle("JSW IRONROD");
        m.setImg(R.drawable.jsw);
        models.add(m);

        m=new Model();
        m.setTitle("KAMDHENU IRONROD");
        m.setImg(R.drawable.kamdhenu);
        models.add(m);





        return models;
    }


}



