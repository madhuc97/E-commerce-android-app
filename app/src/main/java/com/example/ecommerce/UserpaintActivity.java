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

public class UserpaintActivity extends AppCompatActivity {
    DatabaseReference mpaintreff, mcartreff;
    private CartList cartList;

    int[] IMAGES = {R.drawable.asianpaints, R.drawable.nipponpaint, R.drawable.duluxpaint, R.drawable.nerolacpaint, R.drawable.shalimarimg, R.drawable.bergerimg};

    String[] NAMES = {"ASIAN PAINTS", "NIPPON PAINTS", "DULUX PAINTS", "NEROLAC PAINTS", "SHALIMAR PAINTS", "BERGER PAINTS"};

    RecyclerView mListView = null;
    String TAG = "UserpaintActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpaint);
        getSupportActionBar().setTitle("PAINT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();

        mpaintreff = reff.child("Admin").child("Paint_Register");
        mcartreff = reff.child("CartList").child(Prevalent.currentOnlineUser.getUsername()).child("Products");


        mListView  =  findViewById(R.id.ListView);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        mpaintreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Paint_Register> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Paint_Register paint_register = new Paint_Register();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    paint_register.setPaintName(ds.child("paintName").getValue().toString());
                    paint_register.setPaintPrice(ds.child("paintPrice").getValue().toString());
                    paint_register.setPaintStock(ds.child("paintStock").getValue().toString());

                    list.add(paint_register);



                }
                UserpaintActivity.MyRecyclerViewAdapter myRecyclerViewAdapter = new UserpaintActivity.MyRecyclerViewAdapter(list);

                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<UserpaintActivity.MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Paint_Register> mPaintReg = null;


        MyRecyclerViewAdapter(ArrayList<Paint_Register> PaintReg) {

            mPaintReg = PaintReg;

        }

        @NonNull
        @Override
        public UserpaintActivity.MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products, parent, false);
            UserpaintActivity.MyRecyclerViewAdapter.MyViewHolder viewHolder = new UserpaintActivity.MyRecyclerViewAdapter.MyViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final UserpaintActivity.MyRecyclerViewAdapter.MyViewHolder holder, final int position) {


            holder.itemStock.setText(mPaintReg.get(position).getPaintStock());
            holder.itemPrice.setText(mPaintReg.get(position).getPaintPrice());
            holder.itemName.setText(mPaintReg.get(position).getPaintName());
            holder.required_quantity.setText(mPaintReg.get(position).getPaintQuantity());
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



                    cartList.setItemname(mPaintReg.get(position).getPaintName());
                    cartList.setItemprice(mPaintReg.get(position).getPaintPrice());
                    cartList.setItemstock(mPaintReg.get(position).getPaintStock());
                    cartList.setItemquantity(""+holder.required_quantity.getText().toString());








                    //cartList.setItemprice.getText().toString());
                    HashMap<String, Object> paintData = new HashMap<>();
                    paintData.put(mPaintReg.get(position).getPaintName(), cartList);
                    //cementData.put(mCementReg.get(position).getCementStock(),cartList);
                    //cementData.put(mCementReg.get(position).getCementQuantity(),cartList);
                    mcartreff.updateChildren(paintData, new DatabaseReference.CompletionListener() {
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
            return mPaintReg.size();
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




