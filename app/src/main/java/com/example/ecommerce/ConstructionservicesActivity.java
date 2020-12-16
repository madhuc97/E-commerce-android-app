package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ConstructionservicesActivity extends AppCompatActivity {

    private ImageView carpentor,constructor,electrition,painter,plumber,labour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userconstructionservices);
        getSupportActionBar().setTitle("CONSTRUCTION SERVICES");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        carpentor = (ImageView) findViewById(R.id.carpentor);
        constructor = (ImageView) findViewById(R.id.contructor);
         electrition = (ImageView) findViewById(R.id.sellerlectricals) ;
         painter = (ImageView)findViewById(R.id.paint) ;
         plumber = (ImageView)findViewById(R.id.hardware) ;
         labour = (ImageView)findViewById(R.id.electricals) ;


        carpentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ConstructionservicesActivity.this,Usercarpentor.class);
                startActivity(intent);
            }
        });

        constructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ConstructionservicesActivity.this,UserconstructorActivity.class);
                startActivity(intent);
            }
        });

        electrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ConstructionservicesActivity.this,UserelectritionActivity.class);
                startActivity(intent);
            }
        });

        painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ConstructionservicesActivity.this,UserpainterActivity.class);
                startActivity(intent);
            }
        });
        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ConstructionservicesActivity.this,UserplumberActivity.class);
                startActivity(intent);
            }
        });
        labour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ConstructionservicesActivity.this,UserlabourActivity.class);
                startActivity(intent);
            }
        });

    }
}