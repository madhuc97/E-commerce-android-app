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

public class UserpainterActivity extends AppCompatActivity {

    private TextView painter_name,painter_phoneno,painter_description,painter_expertise,painter_experiance,painter_price,painter_servicetype;
    DatabaseReference painterref;
    Painter_register painter_registor;
    RecyclerView mListView = null;
    String TAG ="UserpainterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpainter);

        painter_name = (TextView)findViewById(R.id.paintername);
        painter_phoneno = (TextView)findViewById(R.id.painterphoneno);
        painter_description = (TextView)findViewById(R.id.painterdescription);
        painter_expertise = (TextView)findViewById(R.id.painterdescription4) ;
        painter_experiance = (TextView)findViewById(R.id.painterdescription3) ;
        painter_price = (TextView)findViewById(R.id.painterdescription2) ;
        painter_servicetype = (TextView)findViewById(R.id.painterphoneno2) ;


        painterref = FirebaseDatabase.getInstance().getReference().child("Painter");
        mListView = findViewById(R.id.userpainterView);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        painterref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Painter_register> list = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Painter_register painter_registor = new Painter_register();


                    Log.d(TAG, "SavedData" + ds.getValue().toString());


                    String key = ds.getKey();


                    painter_registor.setPainter_Name(ds.child("paintername").getValue().toString());
                    painter_registor.setPainter_Phoneno(ds.child("painterphoneno").getValue().toString());
                    painter_registor.setPainter_servicetype(ds.child("painterservicetype").getValue().toString());
                    painter_registor.setPainter_expertise(ds.child("painterexpertise").getValue().toString());
                    painter_registor.setPainter_experiance(ds.child("painterexperiance").getValue().toString());
                    painter_registor.setPainter_price(ds.child("painterprice").getValue().toString());
                    painter_registor.setPainter_Description(ds.child("painterdescription").getValue().toString());

                    list.add(painter_registor);



                }
                UserpainterActivity.MyRecyclerViewAdapter myRecyclerViewAdapter = new UserpainterActivity.MyRecyclerViewAdapter(list);

                mListView.setAdapter(myRecyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<UserpainterActivity.MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Painter_register> mPainterReg = null;


        MyRecyclerViewAdapter(ArrayList<Painter_register> PainterReg) {

            mPainterReg = PainterReg;

        }

        @NonNull
        @Override
        public UserpainterActivity.MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.painter, parent, false);
            UserpainterActivity.MyRecyclerViewAdapter.MyViewHolder viewHolder = new UserpainterActivity.MyRecyclerViewAdapter.MyViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserpainterActivity.MyRecyclerViewAdapter.MyViewHolder holder, int position) {


            holder.painter_Name.setText(mPainterReg.get(position).getPainter_Name());
            holder.painter_Phoneno.setText(mPainterReg.get(position).getPainter_Phoneno());
            holder.painter_Description.setText(mPainterReg.get(position).getPainter_Description());
            holder.painter_expertise.setText(mPainterReg.get(position).getPainter_expertise());
            holder.painter_experiance.setText(mPainterReg.get(position).getPainter_experiance());
            holder.painter_servicetype.setText(mPainterReg.get(position).getPainter_servicetype());
            holder.painter_price.setText(mPainterReg.get(position).getPainter_price());
            //holder.imageView.setImageResource(IMAGES[position]);


        }

        @Override
        public int getItemCount() {
            return mPainterReg.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView painter_Name, painter_Phoneno, painter_Description,painter_expertise,painter_experiance,painter_price,painter_servicetype;
            //ImageView imageView;
            //public ImageView imageView;
            //public ImageView itemimage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                painter_Name= itemView.findViewById(R.id.paintername);
                painter_Phoneno = itemView.findViewById(R.id.painterphoneno);
                painter_Description = itemView.findViewById(R.id.painterdescription);
                painter_expertise = itemView.findViewById(R.id.painterdescription4);
                painter_experiance = itemView.findViewById(R.id.painterdescription3);
                painter_price = itemView.findViewById(R.id.painterdescription2);
                painter_servicetype = itemView.findViewById(R.id.painterphoneno2);
                //imageView = (ImageView) itemView.findViewById(R.id.etimageView);

            }
        }
    }
}

