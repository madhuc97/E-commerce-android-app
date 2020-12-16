package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerce.Model.CartList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UserhardwareActivity extends AppCompatActivity {

    DatabaseReference mhardwarereff, mcartreff;
    private CartList cartList;

    int[] IMAGES = {R.drawable.wren, R.drawable.driller, R.drawable.tapariaspanners};

    String[] NAMES = {"WRENCH", "DRILLER", "SPANNERS"};

    RecyclerView mListView = null;
    String TAG = "UserhardwareActivity";
    private Hardware_Register  hardware_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhardware);

        getSupportActionBar().setTitle("HARDWARE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();

        mhardwarereff = reff.child("Admin").child("Hardware_Register");
        mcartreff = reff.child("CartList").child(Prevalent.currentOnlineUser.getUsername()).child("Products");


        mListView  =  findViewById(R.id.ListView);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        mhardwarereff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Hardware_Register> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Hardware_Register hardware_register = new Hardware_Register();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    hardware_register.setHardwareName(ds.child("hardwareName").getValue().toString());
                    hardware_register.setHardwarePrice(ds.child("hardwarePrice").getValue().toString());
                    hardware_register.setHardwareStock(ds.child("hardwareStock").getValue().toString());

                    list.add(hardware_register);



                }
                MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(list);

                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Hardware_Register> mHardwareReg = null;


        MyRecyclerViewAdapter(ArrayList<Hardware_Register> HardwareReg) {

            mHardwareReg = HardwareReg;

        }

        @NonNull
        @Override
        public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products, parent, false);
            MyRecyclerViewAdapter.MyViewHolder viewHolder = new MyRecyclerViewAdapter.MyViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyRecyclerViewAdapter.MyViewHolder holder, final int position) {


            holder.itemStock.setText(mHardwareReg.get(position).getHardwareStock());
            holder.itemPrice.setText(mHardwareReg.get(position).getHardwarePrice());
            holder.itemName.setText(mHardwareReg.get(position).getHardwareName());
            holder.required_quantity.setText(mHardwareReg.get(position).getHardwareQuantity());
            holder.imageView.setImageResource(IMAGES[position]);



            holder.add.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    cartList = new CartList();

                    //final EditText itemQuantity = (EditText) view.findViewById(R.id.etrq);

                    //System.out.println("inside add onlclick!!!---" + itemQuantity.getText().toString());
                    //Toast.makeText(getApplicationContext(), "name=" + itemQuantity.getText(), Toast.LENGTH_SHORT).show();


                    //cartList.setItemquantity(itemQuantity.getText().toString());



                    cartList.setItemname(mHardwareReg.get(position).getHardwareName());
                    cartList.setItemprice(mHardwareReg.get(position).getHardwarePrice());
                    cartList.setItemstock(mHardwareReg.get(position).getHardwareStock());
                    cartList.setItemquantity(""+holder.required_quantity.getText().toString());








                    //cartList.setItemprice.getText().toString());
                    HashMap<String, Object> hardwareData = new HashMap<>();
                    hardwareData.put(mHardwareReg.get(position).getHardwareName(), cartList);
                    //cementData.put(mCementReg.get(position).getCementStock(),cartList);
                    //cementData.put(mCementReg.get(position).getCementQuantity(),cartList);
                    mcartreff.updateChildren(hardwareData, new DatabaseReference.CompletionListener() {
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
        public int getItemCount() {
            return mHardwareReg.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView itemName, itemPrice, itemStock;
            public EditText required_quantity;
            ImageView imageView;
            public Button add;
            //public ImageView imageView;
            //public ImageView itemimage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                itemName = itemView.findViewById(R.id.textView_name);
                itemPrice = itemView.findViewById(R.id.etsellerprice);
                itemStock = itemView.findViewById(R.id.etsellerstock);
                imageView = (ImageView) itemView.findViewById(R.id.etimageView);
                required_quantity = itemView.findViewById(R.id.etrq);
                add = itemView.findViewById(R.id.addtocart);

            }
        }
    }
}
