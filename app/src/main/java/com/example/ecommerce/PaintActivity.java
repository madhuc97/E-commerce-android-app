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

import com.example.ecommerce.Model.CementAdopter;
import com.example.ecommerce.Model.Model;
import com.example.ecommerce.Model.PaintAdopter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class PaintActivity extends AppCompatActivity {
    private Button add;
    private TextView title;
    private EditText stock, price;
    RecyclerView mRecyclerView;
    PaintAdopter paintAdopter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        getSupportActionBar().setTitle("PAINT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        stock = (EditText) findViewById(R.id.etsellerstock);
        price = (EditText) findViewById(R.id.etsellerstockprice);
        add = (Button) findViewById(R.id.selleradd);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paintAdopter = new PaintAdopter(this, getMyList());
        mRecyclerView.setAdapter(paintAdopter);
    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("ASIAN PAINTS");
        m.setImg(R.drawable.asianpaints);
        models.add(m);

        m=new Model();
        m.setTitle("NIPPON PAINTS");
        m.setImg(R.drawable.nipponpaint);
        models.add(m);

        m=new Model();
        m.setTitle("DULUX PAINTS");
        m.setImg(R.drawable.duluxpaint);
        models.add(m);

        m=new Model();
        m.setTitle("NEROLAC PAINTS");
        m.setImg(R.drawable.nerolacpaint);
        models.add(m);


        m=new Model();
        m.setTitle("SHALIMAR PAINTS");
        m.setImg(R.drawable.shalimarimg);
        models.add(m);

        m=new Model();
        m.setTitle("BERGER");
        m.setImg(R.drawable.bergerimg);
        models.add(m);







        return models;
    }


}






