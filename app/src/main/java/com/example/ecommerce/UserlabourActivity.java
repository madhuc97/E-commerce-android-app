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

import com.example.ecommerce.ui.Electrition_register;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserlabourActivity extends AppCompatActivity {

    private TextView labour_name, labour_phoneno, labour_description,labour_expertise,labour_experiance,labour_price,labour_servicetype;
    DatabaseReference labourref;
    Labour_register labour_register;
    RecyclerView mListView = null;
    String TAG = "UserlabourActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpainter);
        labour_name = (TextView) findViewById(R.id.paintername);
        labour_phoneno = (TextView) findViewById(R.id.painterphoneno);
        labour_description = (TextView) findViewById(R.id.painterdescription);
        labour_expertise = (TextView)findViewById(R.id.painterdescription4) ;
        labour_experiance = (TextView)findViewById(R.id.painterdescription3) ;
        labour_price = (TextView)findViewById(R.id.painterdescription2) ;
        labour_servicetype = (TextView)findViewById(R.id.painterphoneno2) ;


        labourref = FirebaseDatabase.getInstance().getReference().child("Labour");
        mListView = findViewById(R.id.userpainterView);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        labourref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Labour_register> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Labour_register labour_register = new Labour_register();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    labour_register.setLabour_Name(ds.child("labourname").getValue().toString());
                    labour_register.setLabour_Phoneno(ds.child("labourphoneno").getValue().toString());
                    labour_register.setLabour_Description(ds.child("labourdescription").getValue().toString());
                    labour_register.setLabour_expertise(ds.child("labourexpertise").getValue().toString());
                    labour_register.setLabour_experiance(ds.child("labourexperiance").getValue().toString());
                    labour_register.setLabour_price(ds.child("labourprice").getValue().toString());
                    labour_register.setLabour_servicetype(ds.child("labourdescription").getValue().toString());

                    list.add(labour_register);


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


            ArrayList<Labour_register> mLabourReg = null;


            MyRecyclerViewAdapter(ArrayList<Labour_register> LabourReg) {

                mLabourReg = LabourReg;

            }

            @NonNull
            @Override
            public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.painter, parent, false);
                MyRecyclerViewAdapter.MyViewHolder viewHolder = new MyRecyclerViewAdapter.MyViewHolder(view);


                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {


                holder.labour_Name.setText(mLabourReg.get(position).getLabour_Name());
                holder.labour_Phoneno.setText(mLabourReg.get(position).getLabour_Phoneno());
                holder.labour_Description.setText(mLabourReg.get(position).getLabour_Description());
                holder.labour_expertise.setText(mLabourReg.get(position).getLabour_expertise());
                holder.labour_experiance.setText(mLabourReg.get(position).getLabour_experiance());
                holder.labour_servicetype.setText(mLabourReg.get(position).getLabour_servicetype());
                holder.labour_price.setText(mLabourReg.get(position).getLabour_price());
                //holder.imageView.setImageResource(IMAGES[position]);


            }

            @Override
            public int getItemCount() {
                return mLabourReg.size();
            }

            class MyViewHolder extends RecyclerView.ViewHolder {


                public TextView labour_Name, labour_Phoneno, labour_Description,labour_expertise,labour_experiance,labour_price,labour_servicetype;
                //ImageView imageView;
                //public ImageView imageView;
                //public ImageView itemimage;

                public MyViewHolder(@NonNull View itemView) {
                    super(itemView);
                    labour_Name = itemView.findViewById(R.id.paintername);
                    labour_Phoneno = itemView.findViewById(R.id.painterphoneno);
                    labour_Description = itemView.findViewById(R.id.painterdescription);
                    labour_expertise = itemView.findViewById(R.id.painterdescription4);
                    labour_experiance = itemView.findViewById(R.id.painterdescription3);
                    labour_price = itemView.findViewById(R.id.painterdescription2);
                    labour_servicetype = itemView.findViewById(R.id.painterphoneno2);
                    //imageView = (ImageView) itemView.findViewById(R.id.etimageView);

                }
            }
        }
    }

