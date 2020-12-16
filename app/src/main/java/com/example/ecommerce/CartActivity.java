package com.example.ecommerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Model.CartList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import static com.example.ecommerce.Prevalent.currentOnlineUser;


public class CartActivity extends AppCompatActivity implements PaymentResultListener {
    DatabaseReference Cartreff, Orderreff;
    CartList cartList;
    //private OrderList orderList;
    private Order_Register order_register;
    private Button checkout;
    private TextView itemQuantity, itemPrice, itemName, totalprice, grandtotal;
    //private TextView date,time;
    private float total_price = 0;
    private float Grandtotalprice = 0 ;
    Double amount;
    int maxid = 0;
    Random random_num = null;


    RecyclerView mListView = null;
    String TAG = "CartActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);



        Checkout.preload(getApplicationContext());
        random_num = new Random();

        getSupportActionBar().setTitle("CART ACTIVITY");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        totalprice = (TextView) findViewById(R.id.textView16);
        itemName = (TextView) findViewById(R.id.textconstructorname);
        itemPrice = (TextView) findViewById(R.id.textconstructorphoneno);
        itemQuantity = (TextView) findViewById(R.id.textconstructordescription);
        checkout = (Button) findViewById(R.id.button);
        grandtotal = (TextView) findViewById(R.id.textView10);

       // date = (TextView)findViewById(R.id.);
        //time = (TextView)findViewById(R.id.);

        /*Orderreff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    maxid = (int) snapshot.getChildrenCount();
                }
                else {
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        order_register = new Order_Register();
        Orderreff = FirebaseDatabase.getInstance().getReference().child("Order_Register").child(Prevalent.currentOnlineUser.getUsername()).child("Order_details");
        //Orderreff = FirebaseDatabase.getInstance().getReference().child("Order_Register");

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             Double amount = Double.valueOf(Double.parseDouble(grandtotal.getText().toString()));
             amount=amount*100;


            startPayment(amount, uniqueNumer);

              /* String saveCurrentDate,saveCurrentTime;
                Calendar calForDate = Calendar.getInstance().getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd,yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calForDate.getTime());



                order_register.setOrderDate(saveCurrentDate);
                order_register.setOrderTime(saveCurrentTime);
                order_register.setOrderTotal(Grandtotalprice);
                order_register.setOrderId(String.valueOf(random_num.nextInt()));


              //  currentOnlineUser.setPhonenumber("9844626189");
                //System.out.println("checking for phone numner-"+currentOnlineUser.getPhonenumber());
                Orderreff.child(currentOnlineUser.getUsername()).setValue(order_register);
                //Orderreff.child(saveCurrentDate).setValue(order_register);*/


            }
        });


        Cartreff = FirebaseDatabase.getInstance().getReference().child("CartList").child(currentOnlineUser.getUsername()).child("Products");

        mListView = findViewById(R.id.CartView);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        Cartreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<CartList> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren())
                {

                    CartList cartList = new CartList();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    cartList.setItemname(ds.child("itemname").getValue().toString());
                    cartList.setItemprice(ds.child("itemprice").getValue().toString());
                    cartList.setItemquantity(ds.child("itemquantity").getValue().toString());

                    float total_price = Float.valueOf(ds.child("itemprice").getValue().toString()) * Float.valueOf(ds.child("itemquantity").getValue().toString());
                    cartList.setTotal_price(String.valueOf(total_price));
                    Grandtotalprice = Grandtotalprice + total_price;
                    System.out.println("Total Price-" + String.valueOf(total_price));
                    //totalprice = float((itemPrice)(itemQuantity));
                    //itemPrice.setText(String.valueOf(itemPrice));
                    //itemQuantity.setText(String.valueOf(itemQuantity));
                    //itemQuantity.setText(String.valueOf(itemQuantity));

                    //cartList.setTotal_price(String.valueOf(totalprice));
                    //System.out.println("totalprice"+totalprice);

                    list.add(cartList);


                }


                MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(list);

                mListView.setAdapter(myRecyclerViewAdapter);
                grandtotal.setText(String.valueOf(Grandtotalprice));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

String uniqueNumer=null;
    public void startPayment(Double amount, String uniqueNumer)
    {

        this.uniqueNumer =String.valueOf(System.currentTimeMillis());
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        checkout.setKeyID("rzp_test_mwCaqfXKBWmzFo");

        /**
         * Set your logo here
         */
        //checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();
/*

            options.put("name", "Rachana");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", "order_DBJOWzybf0sJbbaq1");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "1");//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","9988776655");
            checkout.open(activity, options);
*/


            options.put("name", "Razor Pay");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", amount);
            options.put("order_id",uniqueNumer);//from response of step 3.

            /*JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "7338229913");

            options.put("prefill", preFill);*/

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String razorpayPaymentID){


        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance().getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd,yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());



        order_register.setOrderDate(saveCurrentDate);
        order_register.setOrderTime(saveCurrentTime);
        order_register.setOrderTotal(Grandtotalprice);
        order_register.setOrderId(uniqueNumer);


        /*ArrayList<OrderDeatils> ordersData=new ArrayList<>();

        OrderDeatils orderDeatils=new OrderDeatils();
        orderDeatils.setItemName("item NAME",itemName.getText().toString());
        orderDeatils.setItemPrice("item NAME",itemPrice.getText().toString());



        ordersData.add(orderDeatils);

        order_register.setOrderDetails(ordersData);*/


      Orderreff.setValue(order_register);
         // Orderreff.child(Prevalent.currentOnlineUser.getUsername()).child("Order_details").setValue(order_register);

        Toast.makeText(CartActivity.this,"Payment Succussful",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Success kout" +razorpayPaymentID);


        /*if (task.isSuccessful())
        {


        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderList = new OrderList();

                orderList.setItemname(mCartReg.get(position).getItemname());
                orderList.setItemprice(mCartReg.get(position).getItemname());
                orderList.setItemquantity(mCartReg.get(position).getItemname());

                HashMap<String, Object> orderData = new HashMap<>();
                orderData.put(mCartReg.get(position).getItemname(), orderList);
                Orderreff.updateChildren(orderData, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {


                        if (error != null){
                            Log.d("db error", "--" + error.getMessage());

                    }
                });
            }

        }*/
    }


    @Override
    public void onPaymentError(int i, String s)
    {

        Toast.makeText(CartActivity.this,"Payment Failed",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Error in eerror Razorpay Checkout" +s);
    }



    class MyRecyclerViewAdapter extends RecyclerView.Adapter<CartActivity.MyRecyclerViewAdapter.MyViewHolder>
    {


        ArrayList<CartList> mCartReg = null;


        MyRecyclerViewAdapter(ArrayList<CartList> CartReg) {

            mCartReg = CartReg;

        }

        @NonNull
        @Override
        public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitems, parent, false);
            MyRecyclerViewAdapter.MyViewHolder viewHolder = new MyRecyclerViewAdapter.MyViewHolder(view);


            return viewHolder;
        }



        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


            holder.itemName.setText(mCartReg.get(position).getItemname());
            holder.itemPrice.setText(mCartReg.get(position).getItemprice());
            holder.itemQuantity.setText(mCartReg.get(position).getItemquantity());
            holder.totalprice.setText(mCartReg.get(position).getTotal_price());
            //holder.grandprice.setText(mCartReg.get(position).getGrand_total());

            //holder.imageView.setImageResource(IMAGES[position]);





        }


        @Override
        public int getItemCount() {
            return mCartReg.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView itemName, itemPrice, itemQuantity, totalprice, grandtotal;
            public Button checkout;
            //ImageView imageView;
            //public ImageView imageView;
            //public ImageView itemimage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                itemName = itemView.findViewById(R.id.textconstructorname);
                itemPrice = itemView.findViewById(R.id.textconstructorphoneno);
                itemQuantity = itemView.findViewById(R.id.textconstructordescription);
                totalprice = itemView.findViewById(R.id.textView16);
                grandtotal = itemView.findViewById(R.id.textView10);
                checkout = (Button) findViewById(R.id.button);
                //imageView = (ImageView) itemView.findViewById(R.id.etimageView);

            }
        }
    }
}





