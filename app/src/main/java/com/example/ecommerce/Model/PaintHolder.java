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

public class PaintHolder extends RecyclerView.ViewHolder
{

    ImageView mimageView;
    TextView mtitle;
    EditText mstock, mprice;
    Button add;


    public PaintHolder(@NonNull View itemView) {
        super(itemView);

        this.mimageView = itemView.findViewById(R.id.etimageView);
        this.mtitle = itemView.findViewById(R.id.textView_name);
        this.mstock = itemView.findViewById(R.id.etsellerstock);
        this.mprice = itemView.findViewById(R.id.etsellerstockprice);
        this.add = itemView.findViewById(R.id.selleradd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                adding();
            }
        });
    }

    private void adding() {

        String paint_title = mtitle.getText().toString();
        String paint_stock = mstock.getText().toString();
        String paint_price = mprice.getText().toString();

        if (TextUtils.isEmpty(paint_title)) {
            //Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(paint_stock)) {
            //Toast.makeText(this, " please write your phone no...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(paint_price)) {
            //Toast.makeText(this, " please write your description...", Toast.LENGTH_SHORT).show();
        } else {
            Validate(paint_title, paint_stock, paint_price);
        }
    }

    private void Validate(final String paint_title, final String paint_stock,  final String paint_price) {
        final DatabaseReference paintreff;
        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();

        paintreff = reff.child("Paint_Register");


        paintreff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child(paint_title).exists())) {

                    final HashMap<String, Object> bricksMap = new HashMap<>();
                    bricksMap.put("paintName", paint_title);
                    bricksMap.put("paintStock", paint_stock);
                    bricksMap.put("paintPrice", paint_price);

                    paintreff.child(paint_title).updateChildren(bricksMap)
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




