package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminhomeActivity extends AppCompatActivity {

    private ImageView cement, ironrod,bricks,paint,hardware,electricals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);

        cement=(ImageView) findViewById(R.id.admincement);
        ironrod=(ImageView) findViewById(R.id.adminironrod);
        bricks=(ImageView) findViewById(R.id.adminbrick);
        paint=(ImageView) findViewById(R.id.adminpaint);
        hardware=(ImageView) findViewById(R.id.adminhardware);
        electricals=(ImageView) findViewById(R.id.adminelectrical);


        cement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminhomeActivity.this,AdmincementActivity.class);
                startActivity(intent);
            }
        });

        ironrod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminhomeActivity.this,AdminironActivity.class);
                startActivity(intent);
            }
        });

        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminhomeActivity.this,AdminpaintActivity.class);
                startActivity(intent);
            }
        });
        bricks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminhomeActivity.this,AdminbricksActivity.class);
                startActivity(intent);
            }
        });
        electricals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminhomeActivity.this,AdminelectricalsActivity.class);
                startActivity(intent);
            }
        });

        hardware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminhomeActivity.this, AdminhardwareActivity.class);
                startActivity(intent);
            }
        });




    }
}