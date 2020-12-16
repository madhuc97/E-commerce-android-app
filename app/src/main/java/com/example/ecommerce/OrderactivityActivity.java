package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.ecommerce.Prevalent.currentOnlineUser;


public class OrderactivityActivity extends AppCompatActivity
{
    DatabaseReference Orderreff;
    Order_Register order_register;
    TextView orderid, orderdate, ordertime, ordertotal;



    RecyclerView mListView = null;
    String TAG = "OrderactivityActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orderactivity);

       DatabaseReference reff = FirebaseDatabase.getInstance().getReference();

        orderid = (TextView) findViewById(R.id.et_orderid);
        orderdate = (TextView) findViewById(R.id.et_orderdate);
        ordertime = (TextView) findViewById(R.id.et_ordertime);
        ordertotal = (TextView) findViewById(R.id.et_ordertotal);



        //Orderreff = reff.child("Order_Register");
        Orderreff = reff.child("Order_Register").child(Prevalent.currentOnlineUser.getUsername());
        //Orderreff = reff.child("Order_Register").child(currentOnlineUser.getUsername());



        mListView = findViewById(R.id.orderview);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        Orderreff.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                ArrayList<Order_Register> list = new ArrayList<>();


                 //   Log.d(TAG, ds.child("orderId").getValue().toString())  ;
                  //  Log.d(TAG,ds.child("orderDate").getValue().toString()) ;
                  //  Log.d(TAG,ds.child("orderTime").getValue().toString()) ;
                  //  Log.d(TAG,ds.child("orderTotal").getValue().toString()) ;







              /* // for (DataSnapshot ds : snapshot.getChildren())
                {
                    Order_Register order_register = new Order_Register();

                    String key = ds.getKey();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());
*/
                  for(DataSnapshot ds : snapshot.getChildren())
                  {
                      Order_Register order_register = new Order_Register();

                      Log.d(TAG, "SavedData" + ds.getValue().toString());

                      String key = ds.getKey();

                      order_register.setOrderId(ds.child("orderId").getValue().toString());
                    order_register.setOrderDate(ds.child("orderDate").getValue().toString());
                    order_register.setOrderTime(ds.child("orderTime").getValue().toString());
                    order_register.setOrderTotal(Float.parseFloat(ds.child("orderTotal").getValue().toString()));


                    list.add(order_register);
                }
                MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(list);
                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>
    {
        ArrayList<Order_Register> mOrderReg = null;

        MyRecyclerViewAdapter(ArrayList<Order_Register> orderReg)
        {
            mOrderReg = orderReg;
        }

        @NonNull
        @Override
        public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderitems, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position)
        {
            holder.orderid.setText(mOrderReg.get(position).getOrderId());
            holder.orderdate.setText(mOrderReg.get(position).getOrderDate());
            holder.ordertime.setText(mOrderReg.get(position).getOrderTime());
            holder.ordertotal.setText((String.valueOf(mOrderReg.get(position).getOrderTotal())));

        }

        @Override
        public int getItemCount()
        {
            return mOrderReg.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder
        {
            public TextView orderid, orderdate, ordertime, ordertotal;

            public MyViewHolder(@NonNull View itemView)
            {
                super(itemView);

                orderid = itemView.findViewById(R.id.et_orderid);
                orderdate = itemView.findViewById(R.id.et_orderdate);
                ordertime = itemView.findViewById(R.id.et_ordertime);
                ordertotal = itemView.findViewById(R.id.et_ordertotal);

            }
        }
    }

}