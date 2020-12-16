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

public class UserconstructorActivity extends AppCompatActivity {

    private TextView constructor_Name, constructor_Phoneno,constructor_Description,constructor_expertise,constructor_experiance,constructor_price,constructor_servicetype;
    DatabaseReference constructorref;
    Constructor_registor constructor_registor;

    RecyclerView mListView = null;
    String TAG = "UserconstructorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpainter);

        constructor_Name = (TextView)findViewById(R.id.paintername);
        constructor_Phoneno = (TextView)findViewById(R.id.painterphoneno);
        constructor_Description = (TextView)findViewById(R.id.painterdescription);
        constructor_expertise = (TextView)findViewById(R.id.painterdescription4) ;
        constructor_experiance = (TextView)findViewById(R.id.painterdescription3) ;
        constructor_price = (TextView)findViewById(R.id.painterdescription2) ;
        constructor_servicetype = (TextView)findViewById(R.id.painterphoneno2) ;

        constructorref = FirebaseDatabase.getInstance().getReference().child("Constructor");
        mListView  =  findViewById(R.id.userpainterView);
        mListView.setLayoutManager(new LinearLayoutManager(this));


        constructorref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Constructor_registor> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Constructor_registor constructor_registor = new Constructor_registor();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    constructor_registor.setConstructor_Name(ds.child("constructorname").getValue().toString());
                    constructor_registor.setConstructor_Phoneno(ds.child("constructorphoneno").getValue().toString());
                    constructor_registor.setConstructor_Description(ds.child("constructordescription").getValue().toString());
                    constructor_registor.setConstructor_expertise(ds.child("constructorexpertise").getValue().toString());
                    constructor_registor.setConstructor_experiance(ds.child("constructorexperiance").getValue().toString());
                    constructor_registor.setConstructor_price(ds.child("constructorprice").getValue().toString());
                    constructor_registor.setConstructor_servicetype(ds.child("constructorservicetype").getValue().toString());


                    list.add(constructor_registor);



                }
                MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(list);

                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<UserconstructorActivity.MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Constructor_registor> mConstructorReg = null;


        MyRecyclerViewAdapter(ArrayList<Constructor_registor> ConstructorReg) {

            mConstructorReg = ConstructorReg;

        }

        @NonNull
        @Override
        public UserconstructorActivity.MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.painter, parent, false);
            UserconstructorActivity.MyRecyclerViewAdapter.MyViewHolder viewHolder = new UserconstructorActivity.MyRecyclerViewAdapter.MyViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserconstructorActivity.MyRecyclerViewAdapter.MyViewHolder holder, int position) {


            holder.constructor_Name.setText(mConstructorReg.get(position).getConstructor_Name());
            holder.constructor_Phoneno.setText(mConstructorReg.get(position).getConstructor_Phoneno());
            holder.constructor_Description.setText(mConstructorReg.get(position).getConstructor_Description());
            holder.constructor_expertise.setText(mConstructorReg.get(position).getConstructor_expertise());
            holder.constructor_experiance.setText(mConstructorReg.get(position).getConstructor_experiance());
            holder.constructor_servicetype.setText(mConstructorReg.get(position).getConstructor_servicetype());
            holder.constructor_price.setText(mConstructorReg.get(position).getConstructor_price());
            //holder.imageView.setImageResource(IMAGES[position]);


        }

        @Override
        public int getItemCount() {
            return mConstructorReg.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView constructor_Name, constructor_Phoneno, constructor_Description,constructor_expertise,constructor_experiance,constructor_price,constructor_servicetype;
            //ImageView imageView;
            //public ImageView imageView;
            //public ImageView itemimage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                constructor_Name= itemView.findViewById(R.id.paintername);
                constructor_Phoneno = itemView.findViewById(R.id.painterphoneno);
                constructor_Description = itemView.findViewById(R.id.painterdescription);
                constructor_expertise = itemView.findViewById(R.id.painterdescription4);
                constructor_experiance = itemView.findViewById(R.id.painterdescription3);
                constructor_price = itemView.findViewById(R.id.painterdescription2);
                constructor_servicetype = itemView.findViewById(R.id.painterphoneno2);
                //imageView = (ImageView) itemView.findViewById(R.id.etimageView);

            }
        }
    }
}



