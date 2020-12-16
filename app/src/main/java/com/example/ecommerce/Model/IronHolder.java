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

public class IronHolder extends RecyclerView.ViewHolder {
    ImageView mimageView;
    TextView mtitle;
    EditText mstock, mprice;
    Button add;


    public IronHolder(@NonNull View itemView) {
        super(itemView);

        mimageView = itemView.findViewById(R.id.etimageView);
        mtitle = itemView.findViewById(R.id.textView_name);
        mstock = itemView.findViewById(R.id.etsellerironstock);
        mprice = itemView.findViewById(R.id.etsellerironprice);
        add = itemView.findViewById(R.id.ironaddtocart);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adding();
            }
        });

    }


    private void adding() {
        String iron_title = mtitle.getText().toString();
        String iron_stock = mstock.getText().toString();
        String iron_price = mprice.getText().toString();

        if (TextUtils.isEmpty(iron_title)) {
            //Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(iron_stock)) {
            //Toast.makeText(this, " please write your phone no...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(iron_price)) {
            //Toast.makeText(this, " please write your description...", Toast.LENGTH_SHORT).show();
        } else {
            Validate(iron_title, iron_stock, iron_price);
        }
    }

    private void Validate(final String iron_title, final String iron_stock, final String iron_price) {
        final DatabaseReference ironreff;
        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();

        ironreff = reff.child("Iron_Register");


        ironreff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child(iron_title).exists())) {

                    final HashMap<String, Object> bricksMap = new HashMap<>();
                    bricksMap.put("ironName", iron_title);
                    bricksMap.put("ironStock", iron_stock);
                    bricksMap.put("ironPrice", iron_price);

                    ironreff.child(iron_title).updateChildren(bricksMap)
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



