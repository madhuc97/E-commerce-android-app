package com.example.ecommerce;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.ecommerce.ui.ViewPageAdopter;


import java.util.ArrayList;

import cameo.code.imageslider.SliderFragment;


public class SellerhomeactivityActivity<AdSize> extends AppCompatActivity{
    private SliderFragment mSliderFragment;
    //private ImageView mImageView;
    private ArrayList<String> mImagesUrl = new ArrayList<>();
    private ArrayList<Integer> mImagesRes = new ArrayList<>();


     private ImageView cement,ironrod,brick,paint,hardware,electrical;
     private ImageView carpentor,constructor,plumber,painter,labour,electrition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("SELLER HOMEACTIVITY");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_sellerhomeactivity);





        cement = (ImageView) findViewById(R.id.cement);
        ironrod = (ImageView) findViewById(R.id.ironrod);
        brick = (ImageView) findViewById(R.id.brick);
        paint = (ImageView) findViewById(R.id.paint);
        hardware = (ImageView) findViewById(R.id.hardware);
        electrical = (ImageView) findViewById(R.id.electricals);
        carpentor = (ImageView) findViewById(R.id.carpenter);
        constructor = (ImageView) findViewById(R.id.constructor);
        plumber = (ImageView) findViewById(R.id.plumber);
        painter = (ImageView) findViewById(R.id.painter);
        labour = (ImageView) findViewById(R.id.labour);
        electrition = (ImageView) findViewById(R.id.electrition);


        cement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, SellercementActivity.class);
                startActivity(intent);
            }
        });

        ironrod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, SellerIronrodActivity.class);
                startActivity(intent);
            }
        });


        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, PaintActivity.class);
                startActivity(intent);
            }
        });


        carpentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, CarpentorActivity.class);
                startActivity(intent);
            }
        });
        constructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, SellerconstructorActivity.class);
                startActivity(intent);
            }
        });
        labour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, SellerlabourActivity.class);
                startActivity(intent);
            }
        });
        painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, SellerpainterActivity.class);
                startActivity(intent);
            }
        });

        electrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, SellerelectritionActivity.class);
                startActivity(intent);
            }
        });

        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, SellerplumberActivity.class);
                startActivity(intent);
            }
        });

        brick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, BricksActivity.class);
                startActivity(intent);
            }
        });

        hardware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, HardwareActivity.class);
                startActivity(intent);
            }
        });

        electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerhomeactivityActivity.this, SellerelectricalsActivity.class);
                startActivity(intent);
            }
        });
        mImagesRes.add(R.drawable.construction1);
        mImagesRes.add(R.drawable.construction2);
        mImagesRes.add(R.drawable.constructionimag);
        mImagesRes.add(R.drawable.construction3);
        mImagesRes.add(R.drawable.construction4);

        //mSliderFragment = SliderFragment.createWithPath(mImagesUrl);
        mSliderFragment = SliderFragment.createWithRes(mImagesRes);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.slider_panel, mSliderFragment);
        transaction.commit();
    }
}


