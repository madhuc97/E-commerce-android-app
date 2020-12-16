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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ecommerce.Model.CartList;
import com.example.ecommerce.ui.Bricks_Register;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UserbricksActivity extends AppCompatActivity {

    DatabaseReference mCartRef, mBrickRef;
    DatabaseReference cartlistRef;

    private Button add;
    private TextView itemName, itemStock, itemPrice;
    private EditText itemQuantity;
    private ImageView imageView;
    private CartList cartList;

    int[] IMAGES = {R.drawable.hollowbriks, R.drawable.buildingbrick};


    RecyclerView mListView = null;
    String TAG = "UserbricksActivity";


    private Bricks_Register bricks_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userbricks);


        getSupportActionBar().setTitle("BRICKS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();
        add = (Button) findViewById(R.id.addtocart);
        //imageView = (ImageView) findViewById(R.id.etimageView);
        itemName = (TextView) findViewById(R.id.textView_name);
        itemStock = (TextView) findViewById(R.id.etsellerstock);
        itemPrice = (TextView) findViewById(R.id.etsellerprice);
        itemQuantity = (EditText) findViewById(R.id.etrq);


         /* add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                    addingToCartList();
            }
        });*/


        mBrickRef = reff.child("Admin").child("Bricks_Register");

        mCartRef = reff.child("CartList").child(Prevalent.currentOnlineUser.getUsername()).child("Products");

        mListView = findViewById(R.id.ListView);
        mListView.setLayoutManager(new LinearLayoutManager(this));


        mBrickRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                ArrayList<Bricks_Register> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {


                    Bricks_Register bricks_register = new Bricks_Register();

                    //Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();

                    bricks_register.setBricksName(ds.child("bricksName").getValue().toString());
                    bricks_register.setBricksPrice(ds.child("bricksPrice").getValue().toString());
                    bricks_register.setBricksStock(ds.child("bricksStock").getValue().toString());


                    list.add(bricks_register);
                }

                MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(list);
                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    /*private void addingToCartList() {
        String saveCurrentTime, saveCuurentDate;
        String itemname = itemName.getText().toString();
        //String itemstock = itemStock.getText().toString();
        //String itemprice = itemPrice.getText().toString();


        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCuurentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());


        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("CartList");
        final HashMap<String, Object> cartMap = new HashMap<>();


        //cartMap.put("itemImage",imageView);
        cartMap.put("itemname", itemName.getText().toString());
        cartMap.put("itemstock", itemStock.getText().toString());
        cartMap.put("itemprice", itemPrice.getText().toString());
        cartMap.put("date", saveCuurentDate);
        cartMap.put("time", saveCurrentTime);


        cartListRef.child("User View").child(Prevalent.currentOnlineUser.getPhonenumber()).child("CartList")
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            cartListRef.child("Admin View").child(Prevalent.currentOnlineUser.getPhonenumber()).child("CartList")
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>()
                                    {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(UsercementActivity.this, "Added to Cart List", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                    }
                });


    }*/



        class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Bricks_Register> mBrickReg = null;


        MyRecyclerViewAdapter(ArrayList<Bricks_Register> brickReg) {

            mBrickReg = brickReg;

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
            holder.itemStock.setText(mBrickReg.get(position).getBricksStock());
            holder.itemPrice.setText(mBrickReg.get(position).getBricksPrice());
            holder.itemName.setText(mBrickReg.get(position).getBricksName());
            holder.required_quantity.setText(mBrickReg.get(position).getBricksQuantity());
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


                    cartList.setItemname(mBrickReg.get(position).getBricksName());
                    cartList.setItemprice(mBrickReg.get(position).getBricksPrice());
                    cartList.setItemstock(mBrickReg.get(position).getBricksStock());
                    cartList.setItemquantity(""+holder.required_quantity.getText().toString());








                    //cartList.setItemprice.getText().toString());
                    HashMap<String, Object> brickData = new HashMap<>();
                    brickData.put(mBrickReg.get(position).getBricksName(), cartList);
                    //cementData.put(mCementReg.get(position).getCementStock(),cartList);
                    //cementData.put(mCementReg.get(position).getCementQuantity(),cartList);
                    mCartRef.updateChildren(brickData, new DatabaseReference.CompletionListener() {
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
            return mBrickReg.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView itemName, itemPrice, itemStock;
            public EditText required_quantity;
            public Button add;
            ImageView imageView;

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
