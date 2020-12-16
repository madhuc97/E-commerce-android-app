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

public class Usercarpentor extends AppCompatActivity {


    private TextView carpentor_Name, carpentor_Phoneno,carpentor_Description,carpentor_expertise,carpentor_experiance,carpentor_price,carpentor_servicetype;
    DatabaseReference carpentorref;
    Carpentor_registor carpentor_registor;

    RecyclerView mListView = null;
    String TAG = "UsercarpentorActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpainter);

        carpentor_Name = (TextView) findViewById(R.id.paintername);
        carpentor_Phoneno = (TextView) findViewById(R.id.painterphoneno);
        carpentor_Description = (TextView) findViewById(R.id.painterdescription);
        carpentor_expertise = (TextView)findViewById(R.id.painterdescription4) ;
        carpentor_experiance = (TextView)findViewById(R.id.painterdescription3) ;
        carpentor_price = (TextView)findViewById(R.id.painterdescription2) ;
        carpentor_servicetype = (TextView)findViewById(R.id.painterphoneno2) ;

        carpentorref = FirebaseDatabase.getInstance().getReference().child("Carpentor");
        mListView  =  findViewById(R.id.userpainterView);
        mListView.setLayoutManager(new LinearLayoutManager(this));


        carpentorref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Carpentor_registor> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Carpentor_registor carpentor_registor = new Carpentor_registor();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    carpentor_registor.setCarpentor_Name(ds.child("carpentorname").getValue().toString());
                    carpentor_registor.setCarpentor_Phoneno(ds.child("carpentorphoneno").getValue().toString());
                    carpentor_registor.setCarpentor_Description(ds.child("carpentordescription").getValue().toString());
                    carpentor_registor.setCarpentor_expertise(ds.child("carpentorexpertise").getValue().toString());
                    carpentor_registor.setCarpentor_experiance(ds.child("carpentorexperiance").getValue().toString());
                    carpentor_registor.setCarpentor_price(ds.child("carpentorprice").getValue().toString());
                    carpentor_registor.setCarpentor_servicetype(ds.child("carpentorservicetype").getValue().toString());


                    list.add(carpentor_registor);



                }
                MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(list);

                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<Usercarpentor.MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Carpentor_registor> mCarpentorReg = null;


        MyRecyclerViewAdapter(ArrayList<Carpentor_registor> CarpentorReg) {

            mCarpentorReg = CarpentorReg;

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


            holder.carpentor_Name.setText(mCarpentorReg.get(position).getCarpentor_Name());
            holder.carpentor_Phoneno.setText(mCarpentorReg.get(position).getCarpentor_Phoneno());
            holder.carpentor_Description.setText(mCarpentorReg.get(position).getCarpentor_Description());
            holder.carpentor_expertise.setText(mCarpentorReg.get(position).getCarpentor_expertise());
            holder.carpentor_experiance.setText(mCarpentorReg.get(position).getCarpentor_experiance());
            holder.carpentor_servicetype.setText(mCarpentorReg.get(position).getCarpentor_servicetype());
            holder.carpentor_price.setText(mCarpentorReg.get(position).getCarpentor_price());
            //holder.imageView.setImageResource(IMAGES[position]);


        }

        @Override
        public int getItemCount() {
            return mCarpentorReg.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView carpentor_Name, carpentor_Phoneno, carpentor_Description,carpentor_expertise,carpentor_experiance,carpentor_price,carpentor_servicetype;
            //ImageView imageView;
            //public ImageView imageView;
            //public ImageView itemimage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                carpentor_Name= itemView.findViewById(R.id.paintername);
                carpentor_Phoneno = itemView.findViewById(R.id.painterphoneno);
                carpentor_Description = itemView.findViewById(R.id.painterdescription);
                carpentor_expertise = itemView.findViewById(R.id.painterdescription4);
                carpentor_experiance = itemView.findViewById(R.id.painterdescription3);
                carpentor_price = itemView.findViewById(R.id.painterdescription2);
                carpentor_servicetype = itemView.findViewById(R.id.painterphoneno2);
                //imageView = (ImageView) itemView.findViewById(R.id.etimageView);

            }
        }
    }
}

