package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Homeactivity extends AppCompatActivity {
    private ImageView constructionmaterialsimg, constructionservicesimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        getSupportActionBar().setTitle("HOME");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        constructionmaterialsimg = (ImageView) findViewById(R.id.etconstructionmaterialsimg);
        constructionservicesimg = (ImageView) findViewById(R.id.etconstuctionservicesimg);

        constructionmaterialsimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homeactivity.this, ConstructionmaterialsActivity.class);
                startActivity(intent);
            }
        });

        constructionservicesimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homeactivity.this, ConstructionservicesActivity.class);
                startActivity(intent);
            }
        });
    }
}
