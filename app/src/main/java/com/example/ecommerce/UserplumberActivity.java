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

public class UserplumberActivity extends AppCompatActivity {

    private TextView plumber_name,plumber_phoneno,plumber_description,plumber_expertise,plumber_experiance,plumber_price,plumber_servicetype;
    DatabaseReference plumberref;
    Plumber_register plumber_registor;
    RecyclerView mListView = null;
    String TAG ="UserpainterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpainter);

        plumber_name = (TextView)findViewById(R.id.paintername);
        plumber_phoneno = (TextView)findViewById(R.id.painterphoneno);
        plumber_description = (TextView)findViewById(R.id.painterdescription);
        plumber_expertise = (TextView)findViewById(R.id.painterdescription4) ;
        plumber_experiance = (TextView)findViewById(R.id.painterdescription3) ;
        plumber_price = (TextView)findViewById(R.id.painterdescription2) ;
        plumber_servicetype = (TextView)findViewById(R.id.painterphoneno2) ;

        plumberref = FirebaseDatabase.getInstance().getReference().child("Plumber");
        mListView = findViewById(R.id.userpainterView);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        plumberref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Plumber_register> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Plumber_register plumber_registor = new Plumber_register();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    plumber_registor.setPlumber_Name(ds.child("plumbername").getValue().toString());
                    plumber_registor.setPlumber_Phoneno(ds.child("plumberphoneno").getValue().toString());
                    plumber_registor.setPlumber_Description(ds.child("plumberdescription").getValue().toString());
                    plumber_registor.setPlumber_expertise(ds.child("plumberexpertise").getValue().toString());
                    plumber_registor.setPlumber_experiance(ds.child("plumberexperiance").getValue().toString());
                    plumber_registor.setPlumber_price(ds.child("plumberprice").getValue().toString());
                    plumber_registor.setPlumber_servicetype(ds.child("plumberdescription").getValue().toString());


                    list.add(plumber_registor);



                }
                UserplumberActivity.MyRecyclerViewAdapter myRecyclerViewAdapter = new UserplumberActivity.MyRecyclerViewAdapter(list);

                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<UserplumberActivity.MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Plumber_register> mPlumberReg = null;


        MyRecyclerViewAdapter(ArrayList<Plumber_register> PlumberReg) {

            mPlumberReg = PlumberReg;

        }

        @NonNull
        @Override
        public UserplumberActivity.MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.painter, parent, false);
            UserplumberActivity.MyRecyclerViewAdapter.MyViewHolder viewHolder = new UserplumberActivity.MyRecyclerViewAdapter.MyViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserplumberActivity.MyRecyclerViewAdapter.MyViewHolder holder, int position) {


            holder.plumber_Name.setText(mPlumberReg.get(position).getPlumber_Name());
            holder.plumber_Phoneno.setText(mPlumberReg.get(position).getPlumber_Phoneno());
            holder.plumber_Description.setText(mPlumberReg.get(position).getPlumber_Description());
            holder.plumber_expertise.setText(mPlumberReg.get(position).getPlumber_expertise());
            holder.plumber_experiance.setText(mPlumberReg.get(position).getPlumber_experiance());
            holder.plumber_servicetype.setText(mPlumberReg.get(position).getPlumber_servicetype());
            holder.plumber_price.setText(mPlumberReg.get(position).getPlumber_price());
            //holder.imageView.setImageResource(IMAGES[position]);


        }

        @Override
        public int getItemCount() {
            return mPlumberReg.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView plumber_Name, plumber_Phoneno, plumber_Description,plumber_expertise,plumber_experiance,plumber_price,plumber_servicetype;
            //ImageView imageView;
            //public ImageView imageView;
            //public ImageView itemimage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                plumber_Name= itemView.findViewById(R.id.paintername);
                plumber_Phoneno = itemView.findViewById(R.id.painterphoneno);
                plumber_Description = itemView.findViewById(R.id.painterdescription);
                plumber_expertise = itemView.findViewById(R.id.painterdescription4);
                plumber_experiance = itemView.findViewById(R.id.painterdescription3);
                plumber_price = itemView.findViewById(R.id.painterdescription2);
                plumber_servicetype = itemView.findViewById(R.id.painterphoneno2);
                //imageView = (ImageView) itemView.findViewById(R.id.etimageView);

            }
        }
    }
}

