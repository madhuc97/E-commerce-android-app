package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Model.Model;
import com.example.ecommerce.Model.MyAdopter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BricksActivity extends AppCompatActivity {
    private Button add;
    private TextView title;
    private EditText stock, price;
    RecyclerView mRecyclerView;
    MyAdopter myAdopter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("BRICKS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_bricks);

        stock = (EditText) findViewById(R.id.etsellerironstock);
        price = (EditText) findViewById(R.id.etsellerironprice);
        add = (Button) findViewById(R.id.ironaddtocart);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdopter = new MyAdopter(this, getMyList());
        mRecyclerView.setAdapter(myAdopter);




    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("Hollow Bricks");
        m.setImg(R.drawable.hollowbriks);
        models.add(m);

        m=new Model();
        m.setTitle("Venkateshwara Bricks");
        m.setImg(R.drawable.buildingbrick);
        models.add(m);




        return models;
    }


}