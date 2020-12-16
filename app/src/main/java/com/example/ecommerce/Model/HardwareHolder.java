package com.example.ecommerce.Model;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class HardwareHolder extends RecyclerView.ViewHolder {

    ImageView mimageView;
    TextView mtitle;
    EditText mstock,mprice;
    Button add;
    public HardwareHolder(@NonNull View itemView) {


        super(itemView);

        mimageView=itemView.findViewById(R.id.etimageView);
        mtitle=itemView.findViewById(R.id.textView_name);
        mstock=itemView.findViewById(R.id.etsellerironstock);
        mprice=itemView.findViewById(R.id.etsellerironprice);
        add=itemView.findViewById(R.id.ironaddtocart);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adding();
            }
        });

    }

    private void adding() {
        String hardware_title = mtitle.getText().toString();
        String hardware_stock = mstock.getText().toString();
        String hardware_price = mprice.getText().toString();


        if (TextUtils.isEmpty(hardware_title)) {
            //Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(hardware_stock)) {
            //Toast.makeText(this, " please write your phone no...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(hardware_price)) {
            //Toast.makeText(this, " please write your description...", Toast.LENGTH_SHORT).show();
        } else {
            Validate(hardware_title, hardware_stock, hardware_price);
        }
    }


    private void Validate(final String hardware_title, final String hardware_stock, final String hardware_price) {


        final DatabaseReference hardwarereff;
        DatabaseReference reff= FirebaseDatabase.getInstance().getReference();

                hardwarereff = reff.child("Hardware_Register");





        hardwarereff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child(hardware_title).exists())) {

                    final HashMap<String, Object> bricksMap = new HashMap<>();
                    bricksMap.put("hardwareName", hardware_title);
                    bricksMap.put("hardwareStock", hardware_stock);
                    bricksMap.put("hardwarePrice", hardware_price);

                    hardwarereff.child(hardware_title).updateChildren(bricksMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        //Toast.makeText(BricksActivity.this, "Added Succussfully", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}
