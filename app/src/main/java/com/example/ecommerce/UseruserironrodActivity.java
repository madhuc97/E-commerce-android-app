package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ecommerce.Model.CartList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UseruserironrodActivity extends AppCompatActivity
{

    DatabaseReference mironreff,mcartreff;
    private CartList cartList;

    int[] IMAGES = {R.drawable.tataironrods, R.drawable.srmbtmt, R.drawable.essar, R.drawable.jsw, R.drawable.kamdhenu};

    String[] NAMES = {"TATA IRONROD", "SRMBTMT", "ESSAR IRONROD", "JSW IRONROD", "KAMDHENU IRONROD"};

    RecyclerView mListView = null;

    String TAG = "UseruserironrodActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useruserironrod);
        getSupportActionBar().setTitle("IRON");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();

        mironreff = reff.child("Admin").child("Iron_Register");
        mcartreff = reff.child("CartList").child(Prevalent.currentOnlineUser.getUsername()).child("Products");

        mListView = findViewById(R.id.ironrodlistview);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        mironreff.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Iron_Register> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {


                    Iron_Register iron_register = new Iron_Register();

                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    iron_register.setIronName(ds.child("ironName").getValue().toString());
                    iron_register.setIronPrice(ds.child("ironPrice").getValue().toString());
                    iron_register.setIronStock(ds.child("ironStock").getValue().toString());

                    list.add(iron_register);


                }
                UseruserironrodActivity.MyRecyclerViewAdapter myRecyclerViewAdapter=new UseruserironrodActivity.MyRecyclerViewAdapter(list);

                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    class MyRecyclerViewAdapter extends RecyclerView.Adapter< UseruserironrodActivity.MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Iron_Register> mIronReg = null;


        MyRecyclerViewAdapter(ArrayList<Iron_Register> IronReg) {

            mIronReg = IronReg;

        }

        @NonNull
        @Override
        public  UseruserironrodActivity.MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products, parent, false);
            UseruserironrodActivity.MyRecyclerViewAdapter.MyViewHolder viewHolder = new  UseruserironrodActivity.MyRecyclerViewAdapter.MyViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final UseruserironrodActivity.MyRecyclerViewAdapter.MyViewHolder holder, final int position)
        {



            holder.itemStock.setText(mIronReg .get(position).getIronStock());
            holder.itemPrice.setText(mIronReg .get(position).getIronPrice());
            holder.itemName.setText(mIronReg .get(position).getIronName());
            holder.imageView.setImageResource(IMAGES[position]);
            holder.required_quantity.setText(mIronReg.get(position).getIronQuantity());


            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartList = new CartList();

                    cartList.setItemname(mIronReg.get(position).getIronName());
                    cartList.setItemprice(mIronReg.get(position).getIronPrice());
                    cartList.setItemstock(mIronReg.get(position).getIronStock());
                    cartList.setItemquantity(""+holder.required_quantity.getText().toString());

                    HashMap<String, Object> ironData = new HashMap<>();
                    ironData.put(mIronReg.get(position).getIronName(), cartList);
                    //cementData.put(mCementReg.get(position).getCementStock(),cartList);
                    //cementData.put(mCementReg.get(position).getCementQuantity(),cartList);
                    mcartreff.updateChildren(ironData, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {


                            if (error != null)
                                Log.d("db error", "--" + error.getMessage());

                        }
                    });

                }
            });
        }

        @Override
        public int getItemCount()
        {
            return mIronReg .size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView itemName, itemPrice, itemStock;
            ImageView imageView;
            public Button add;
            public EditText required_quantity;
            //public ImageView imageView;
            //public ImageView itemimage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                add = itemView.findViewById(R.id.addtocart);
                itemName = itemView.findViewById(R.id.textView_name);
                itemPrice = itemView.findViewById(R.id.etsellerprice);
                itemStock = itemView.findViewById(R.id.etsellerstock);
                imageView= (ImageView) itemView.findViewById(R.id.etimageView);
                required_quantity = itemView.findViewById(R.id.etrq);

            }
        }
    }


}


