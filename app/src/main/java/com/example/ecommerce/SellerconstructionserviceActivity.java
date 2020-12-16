package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SellerconstructionserviceActivity extends AppCompatActivity {

    private ImageView carentor, constructor,electrition,painter,plumber,labour;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerconstructionservice);
        getSupportActionBar().setTitle("CONSTRUCTION SERVICE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        carentor = (ImageView) findViewById(R.id.carpentor);
        constructor = (ImageView)  findViewById(R.id.contructor);
        electrition = (ImageView) findViewById(R.id.sellerlectricals) ;
        painter = (ImageView) findViewById(R.id.paint);
        plumber = (ImageView)findViewById(R.id.hardware) ;
        labour = (ImageView)findViewById(R.id.electricals) ;


        carentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerconstructionserviceActivity.this,CarpentorActivity.class);
                startActivity(intent);
            }
        });


        constructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerconstructionserviceActivity.this,SellerconstructorActivity.class);
                startActivity(intent);
            }
        });

        electrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerconstructionserviceActivity.this,SellerelectritionActivity.class);
                startActivity(intent);
            }
        });

        painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerconstructionserviceActivity.this,SellerpainterActivity.class);
                startActivity(intent);
            }
        });

        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerconstructionserviceActivity.this,SellerplumberActivity.class);
                startActivity(intent);
            }
        });

        labour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerconstructionserviceActivity.this,SubscribeActivity.class);
                startActivity(intent);
            }
        });


    }
}