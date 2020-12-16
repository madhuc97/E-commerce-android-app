package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SellerconstructionmaterialActivity extends AppCompatActivity {
    private ImageView cement,paint,ironrods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerconstructionmaterial);
        getSupportActionBar().setTitle("CONSTRUCTION MATERIAL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cement = (ImageView) findViewById(R.id.cement);
        paint = (ImageView) findViewById(R.id.paint);
        ironrods = (ImageView)findViewById(R.id.ironrods);

        cement.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                Intent intent = new Intent(SellerconstructionmaterialActivity.this,SellercementActivity.class);
                startActivity(intent);
            }
        });


        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerconstructionmaterialActivity.this, PaintActivity.class);
                startActivity(intent);

            }
        });

        ironrods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerconstructionmaterialActivity.this, SellerIronrodActivity.class);
                startActivity(intent);

            }
        });

    }
}