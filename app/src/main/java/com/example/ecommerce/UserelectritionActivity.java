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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserelectritionActivity extends AppCompatActivity {

    private TextView electrition_name,electrition_phoneno,electrition_description,electrition_expertise,electrition_experiance,electrition_price,electrition_servicetype;
    DatabaseReference electritionref;
    Electrition_register electrition_registor;
    RecyclerView mListView = null;
    String TAG ="UserelectritionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpainter);


        electrition_name = (TextView)findViewById(R.id.paintername);
        electrition_phoneno = (TextView)findViewById(R.id.painterphoneno);
        electrition_description = (TextView)findViewById(R.id.painterdescription);
        electrition_expertise = (TextView)findViewById(R.id.painterdescription4) ;
        electrition_experiance = (TextView)findViewById(R.id.painterdescription3) ;
        electrition_price = (TextView)findViewById(R.id.painterdescription2) ;
        electrition_servicetype = (TextView)findViewById(R.id.painterphoneno2) ;


        electritionref = FirebaseDatabase.getInstance().getReference().child("ELectrition");
        mListView = findViewById(R.id.userpainterView);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        electritionref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Electrition_register> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Electrition_register electrition_registor = new Electrition_register();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    electrition_registor.setElectrition_Name(ds.child("electritionname").getValue().toString());
                    electrition_registor.setElectrition_Phoneno(ds.child("electritionphoneno").getValue().toString());
                    electrition_registor.setElectrition_Description(ds.child("electritiondescription").getValue().toString());
                    electrition_registor.setElectrition_expertise(ds.child("electritionexpertise").getValue().toString());
                    electrition_registor.setElectrition_experiance(ds.child("electritionexperiance").getValue().toString());
                    electrition_registor.setElectrition_price(ds.child("electritionprice").getValue().toString());
                    electrition_registor.setElectrition_servicetype(ds.child("electritionservicetype").getValue().toString());

                    list.add(electrition_registor);



                }
                MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(list);

                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<UserelectritionActivity.MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Electrition_register> mElectritionReg = null;


        MyRecyclerViewAdapter(ArrayList<Electrition_register> ElectritionReg) {

            mElectritionReg = ElectritionReg;

        }

        @NonNull
        @Override
        public UserelectritionActivity.MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.painter, parent, false);
            UserelectritionActivity.MyRecyclerViewAdapter.MyViewHolder viewHolder = new UserelectritionActivity.MyRecyclerViewAdapter.MyViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserelectritionActivity.MyRecyclerViewAdapter.MyViewHolder holder, int position) {


            holder.electrition_Name.setText(mElectritionReg.get(position).getElectrition_Name());
            holder.electrition_Phoneno.setText(mElectritionReg.get(position).getElectrition_Phoneno());
            holder.electrition_Description.setText(mElectritionReg.get(position).getElectrition_Description());
            holder.electrition_expertise.setText(mElectritionReg.get(position).getElectrition_expertise());
            holder.electrition_experiance.setText(mElectritionReg.get(position).getElectrition_experiance());
            holder.electrition_servicetype.setText(mElectritionReg.get(position).getElectrition_servicetype());
            holder.electrition_price.setText(mElectritionReg.get(position).getElectrition_price());

            //holder.imageView.setImageResource(IMAGES[position]);


        }

        @Override
        public int getItemCount() {
            return mElectritionReg.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView electrition_Name, electrition_Phoneno, electrition_Description,electrition_expertise,electrition_experiance,electrition_price,electrition_servicetype;
            //ImageView imageView;
            //public ImageView imageView;
            //public ImageView itemimage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                electrition_Name= itemView.findViewById(R.id.paintername);
                electrition_Phoneno = itemView.findViewById(R.id.painterphoneno);
                electrition_Description = itemView.findViewById(R.id.painterdescription);
                electrition_expertise = itemView.findViewById(R.id.painterdescription4);
                electrition_experiance = itemView.findViewById(R.id.painterdescription3);
                electrition_price = itemView.findViewById(R.id.painterdescription2);
                electrition_servicetype = itemView.findViewById(R.id.painterphoneno2);
                //imageView = (ImageView) itemView.findViewById(R.id.etimageView);

            }
        }
    }
}