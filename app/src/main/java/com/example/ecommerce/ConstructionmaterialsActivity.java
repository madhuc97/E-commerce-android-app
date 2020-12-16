package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ConstructionmaterialsActivity extends AppCompatActivity {
    private ImageView cement,paint,ironrod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userconstructionmaterials);
        getSupportActionBar().setTitle("CONSTRUCTION MATERIALS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cement = (ImageView) findViewById(R.id.cement);
        paint =  (ImageView)findViewById(R.id.paint);
        ironrod =  (ImageView)findViewById(R.id.ironrods);

        cement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConstructionmaterialsActivity.this, UsercementActivity.class);
                startActivity(intent);
            }
        });
        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConstructionmaterialsActivity.this, UserpaintActivity.class);
                startActivity(intent);
            }
        });

        ironrod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConstructionmaterialsActivity.this,  UseruserironrodActivity.class);
                startActivity(intent);
            }
        });

    }
}